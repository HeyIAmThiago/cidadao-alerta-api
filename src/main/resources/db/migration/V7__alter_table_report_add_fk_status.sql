-- Adiciona a coluna se ainda não existir
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'report' AND column_name = 'status_report_id'
  ) THEN
    ALTER TABLE report ADD COLUMN status_report_id UUID;
  END IF;
END $$;

-- Garante que o status 'PENDENTE' existe
INSERT INTO status_report (id, status)
SELECT gen_random_uuid(), 'PENDENTE'
WHERE NOT EXISTS (
  SELECT 1 FROM status_report WHERE status = 'PENDENTE'
);

-- Atualiza todos os reports com status_report_id NULL
DO $$
DECLARE
  pendente_id UUID;
BEGIN
  SELECT id INTO pendente_id FROM status_report WHERE status = 'PENDENTE' LIMIT 1;

  UPDATE report
  SET status_report_id = pendente_id
  WHERE status_report_id IS NULL;
END $$;

-- Torna a coluna obrigatória
ALTER TABLE report
  ALTER COLUMN status_report_id SET NOT NULL;

-- Adiciona a foreign key
ALTER TABLE report
  ADD CONSTRAINT fk_status_report
  FOREIGN KEY (status_report_id) REFERENCES status_report(id);
