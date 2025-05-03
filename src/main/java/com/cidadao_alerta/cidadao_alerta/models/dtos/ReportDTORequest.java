package com.cidadao_alerta.cidadao_alerta.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record ReportDTORequest(

  @NotBlank(message = "O titulo é obrigatório;")
  String title,

  @NotBlank(message = "A descrição é obrigatória.")
  String description,

  String imageUrl
) {}
