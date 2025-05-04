package com.cidadao_alerta.cidadao_alerta.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
  
  
}
