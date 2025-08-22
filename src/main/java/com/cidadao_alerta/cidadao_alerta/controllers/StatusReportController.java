package com.cidadao_alerta.cidadao_alerta.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cidadao_alerta.cidadao_alerta.models.dtos.StatusReportDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.StatusReportDTOResponse;
import com.cidadao_alerta.cidadao_alerta.services.StatusReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/status")
@RequiredArgsConstructor
public class StatusReportController {
  
  private final StatusReportService statusReportService;

  @GetMapping
  public ResponseEntity<List<StatusReportDTOResponse>> getAllStatus() {
    List<StatusReportDTOResponse> status = this.statusReportService.findAllDTO();

    return ResponseEntity.status(HttpStatus.OK).body(status);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<StatusReportDTOResponse> getStatusById(@PathVariable UUID id) {
    StatusReportDTOResponse status = this.statusReportService.findStatusDTO(id);

    return ResponseEntity.status(HttpStatus.OK).body(status);
  }

  @PostMapping
  public ResponseEntity<StatusReportDTOResponse> create(
    @RequestBody StatusReportDTORequest statusReportDTORequest,
    UriComponentsBuilder uriBuilder
  ) {
    StatusReportDTOResponse status = this.statusReportService.createStatus(statusReportDTORequest);
    URI uri = uriBuilder.path("/status/{id}").buildAndExpand(status.id()).toUri();

    return ResponseEntity.created(uri).body(status);
  }

}
