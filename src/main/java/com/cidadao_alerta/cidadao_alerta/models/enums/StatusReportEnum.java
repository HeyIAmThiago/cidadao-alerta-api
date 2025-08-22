package com.cidadao_alerta.cidadao_alerta.models.enums;

public enum StatusReportEnum {
  PENDENTE("pendente"),
  EM_ANALISE("em análise"),
  RESOLVIDO("resolvido"),
  ARQUIVADO("arquivado");

  private final String descricao;

  StatusReportEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}

