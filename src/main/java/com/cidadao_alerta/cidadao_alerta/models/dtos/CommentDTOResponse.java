package com.cidadao_alerta.cidadao_alerta.models.dtos;


import java.util.UUID;


public record CommentDTOResponse(
    UUID id,
    String text,
    UUID author_id,
    String author_name,
    UUID report_id
) {
    
}