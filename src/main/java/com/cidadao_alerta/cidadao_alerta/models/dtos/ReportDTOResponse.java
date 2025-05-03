package com.cidadao_alerta.cidadao_alerta.models.dtos;

import java.util.UUID;

import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;

public record ReportDTOResponse(
  UUID id,
  String title,
  String description,
  String imageUrl
) {
  public ReportDTOResponse(ReportEntity report) {
    this(
      report.getId(),
      report.getTitle(),
      report.getDescription(),
      report.getImageUrl()
    );
  }
}
