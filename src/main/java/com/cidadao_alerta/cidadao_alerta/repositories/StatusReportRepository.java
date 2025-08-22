package com.cidadao_alerta.cidadao_alerta.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cidadao_alerta.cidadao_alerta.models.entities.StatusReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;

public interface StatusReportRepository extends JpaRepository<StatusReportEntity, UUID> {
  
  Optional<StatusReportEntity> findByStatus(StatusReportEnum status);
}
