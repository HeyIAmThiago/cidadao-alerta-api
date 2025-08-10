package com.cidadao_alerta.cidadao_alerta.models.mappers;

import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.CommentEntity;
import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CommentMapper {



    public static CommentDTOResponse toDto(CommentEntity commentEntity) {

        var newDto = new CommentDTOResponse();

        newDto.setId(commentEntity.getId());

        newDto.setText(commentEntity.getText());

        newDto.setAuthor_id(commentEntity.getAuthor().getId());

        newDto.setAuthor_name(commentEntity.getAuthor().getName());

        newDto.setReport_id(commentEntity.getReport().getId());

        return newDto;

    }

    public static CommentEntity toEntity(CommentDTORequest comment, UserEntity user, ReportEntity report) {

        return new CommentEntity(null, comment.getText(), 0L, LocalDateTime.now(), LocalDateTime.now(), user, report);
    }



}
