package com.cidadao_alerta.cidadao_alerta.controllers;


import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.CommentDTOResponse;
import com.cidadao_alerta.cidadao_alerta.services.CommentService;
import com.cidadao_alerta.cidadao_alerta.wrappers.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/comment")
    ResponseEntity<ApiResponseFormat<List<CommentDTOResponse>>> getAllComments() {
        var allCommentsToReturn = commentService.getAllComments();

        return ResponseEntity.ok(
                new ApiResponseFormat<>(200, "Request was ok!", allCommentsToReturn)
        );

    }

    @GetMapping("/comment/{commentId}")
    @Operation(summary = "Find an specific comment using its ID",
        responses = {
        @ApiResponse(responseCode = "200", description = "Comment found"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })


    ResponseEntity<CommentDTOResponse> getCommentById(@PathVariable UUID commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));

    }

    @PostMapping("/comment")

    ResponseEntity<ApiResponseFormat<CommentDTOResponse>> create(@RequestBody CommentDTORequest comment) {

        return ResponseEntity.ok(
                new ApiResponseFormat<>(200, "Request was ok!", commentService.create(comment))
        );
    }

    @DeleteMapping("/comment/{commentId}")

    ResponseEntity<CommentDTOResponse> deleteCommentById(@PathVariable UUID commentId) {
        return ResponseEntity.ok(commentService.deleteById(commentId));
    }
}
