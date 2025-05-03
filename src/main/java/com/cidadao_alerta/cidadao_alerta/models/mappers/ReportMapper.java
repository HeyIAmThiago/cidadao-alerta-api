package com.cidadao_alerta.cidadao_alerta.models.mappers;

import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;

public class ReportMapper {
  
  public static ReportEntity dtoToEntity(ReportDTORequest dto) {
    return ReportEntity.builder()
      .title(dto.title())
      .description(dto.description())
      .imageUrl(dto.imageUrl())
      .build()
    ;
  }
}
