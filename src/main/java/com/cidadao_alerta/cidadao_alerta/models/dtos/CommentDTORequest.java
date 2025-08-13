package com.cidadao_alerta.cidadao_alerta.models.dtos;

import java.util.UUID;

public record CommentDTORequest(
        String text,
        UUID author_id,
        UUID report_id
) {

}
