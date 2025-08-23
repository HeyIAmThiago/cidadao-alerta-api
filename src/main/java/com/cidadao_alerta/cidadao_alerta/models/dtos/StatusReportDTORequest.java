package com.cidadao_alerta.cidadao_alerta.models.dtos;

import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;

import jakarta.validation.constraints.NotBlank;

public record StatusReportDTORequest(
  @NotBlank(message = "Status não pode ser vazio.")
  StatusReportEnum status 
) {
  
}
