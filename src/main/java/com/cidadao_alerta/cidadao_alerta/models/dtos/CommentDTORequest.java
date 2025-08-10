package com.cidadao_alerta.cidadao_alerta.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentDTORequest {

    private String text;

    private UUID author_id;
    private UUID report_id;

}
