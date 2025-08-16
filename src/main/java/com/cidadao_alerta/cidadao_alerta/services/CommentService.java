package com.cidadao_alerta.cidadao_alerta.services;


import com.cidadao_alerta.cidadao_alerta.exceptions.ResourceNotFoundException;
import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.CommentEntity;
import com.cidadao_alerta.cidadao_alerta.models.mappers.CommentMapper;
import com.cidadao_alerta.cidadao_alerta.repositories.CommentRepository;
import com.cidadao_alerta.cidadao_alerta.repositories.ReportRepository;
import com.cidadao_alerta.cidadao_alerta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportRepository reportRepository;

    public List<CommentDTOResponse> getAllComments() {

        List<CommentEntity> allComments = commentRepository.findAll();



        if (allComments.isEmpty()) {
            throw new ResourceNotFoundException("No resource found!");
        };

        ArrayList<CommentDTOResponse> allCommentsToReturn = new ArrayList<>();

        for (CommentEntity comment : allComments)
            allCommentsToReturn.add(CommentMapper.toDto(comment));



        return allCommentsToReturn;

    }

    public CommentDTOResponse getCommentById(UUID commentId) {

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + commentId + " not found."));


        return CommentMapper.toDto(comment);

    }



    public CommentDTOResponse create(CommentDTORequest comment) {

        var user = userRepository.findById(comment.author_id())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        var report = reportRepository.findById(comment.report_id())
                .orElseThrow(() -> new ResourceNotFoundException("Report does not exist"));

        var resource = CommentMapper.toEntity(comment, user, report);

        commentRepository.save(resource);

        return CommentMapper.toDto(resource);



    }

    public CommentDTOResponse deleteById(UUID commentId) {

        var comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        commentRepository.deleteById(commentId);

        return CommentMapper.toDto(comment);
    }
}

