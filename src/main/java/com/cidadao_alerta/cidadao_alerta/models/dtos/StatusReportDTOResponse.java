package com.cidadao_alerta.cidadao_alerta.models.dtos;

import java.util.UUID;

import com.cidadao_alerta.cidadao_alerta.models.entities.StatusReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;

public record StatusReportDTOResponse(
  UUID id,
  StatusReportEnum status,
  String descricao
) {
  public StatusReportDTOResponse(StatusReportEntity statusReportEntity) {
    this(statusReportEntity.getId(), statusReportEntity.getStatus(), statusReportEntity.getStatus().getDescricao());
  }
}
