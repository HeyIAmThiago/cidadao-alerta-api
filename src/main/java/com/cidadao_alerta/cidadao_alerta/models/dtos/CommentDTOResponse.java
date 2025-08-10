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

public class CommentDTOResponse {

    private UUID id;
    private String text;

    private UUID author_id;
    private String author_name;
    private UUID report_id;



}
