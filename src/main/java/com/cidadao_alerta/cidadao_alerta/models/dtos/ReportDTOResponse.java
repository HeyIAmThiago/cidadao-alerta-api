package com.cidadao_alerta.cidadao_alerta.models.dtos;

import java.util.List;
import java.util.UUID;

import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;

public record ReportDTOResponse(
  UUID id,
  String title,
  String description,
  String imageUrl,
  List<CategoryDTOResponse> categories,
  StatusReportEnum status
) {
  public ReportDTOResponse(ReportEntity report, List<CategoryDTOResponse> categoriesDTO) {
    this(
      report.getId(),
      report.getTitle(),
      report.getDescription(),
      report.getImageUrl(),
      categoriesDTO,
      report.getStatus() != null ? report.getStatus().getStatus() : null
    );
  }
}
