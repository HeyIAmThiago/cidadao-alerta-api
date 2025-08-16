package com.cidadao_alerta.cidadao_alerta.models.mappers;

import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.CommentEntity;
import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentMapper {

    public static CommentDTOResponse toDto(CommentEntity commentEntity) {

        UUID id  = commentEntity.getId();
        String text = commentEntity.getText();
        UUID author_id = commentEntity.getAuthor().getId();
        String author_name = commentEntity.getAuthor().getName();
        UUID report_id = commentEntity.getReport().getId();

        return new CommentDTOResponse(id, text, author_id, author_name, report_id);

    }

    public static CommentEntity toEntity(CommentDTORequest comment, UserEntity user, ReportEntity report) {
        return new CommentEntity(null, comment.text(), 0L, LocalDateTime.now(), LocalDateTime.now(), user, report);
    }
}
