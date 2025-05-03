package com.cidadao_alerta.cidadao_alerta.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTOResponse;
import com.cidadao_alerta.cidadao_alerta.services.ReportService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/denuncia")
@RequiredArgsConstructor
public class ReportController {
  
  private final ReportService reportService;

  @GetMapping
  public ResponseEntity<List<ReportDTOResponse>> getAllReports() {
    List<ReportDTOResponse> reports = this.reportService.getAllReports();

    return ResponseEntity.status(HttpStatus.OK).body(reports);
  }

  @GetMapping(params = { "page", "size" })
  public ResponseEntity<Page<ReportDTOResponse>> getReports(
    @RequestParam("page") int page,
    @RequestParam("size") int size
  ) {
    Page<ReportDTOResponse> reports = this.reportService.getReports(
      PageRequest.of(page, size)
    );

    return ResponseEntity.status(HttpStatus.OK).body(reports);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ReportDTOResponse> getReportById(
    @PathVariable UUID id
  ) {
    ReportDTOResponse report = this.reportService.getReportById(id);

    return ResponseEntity.status(HttpStatus.OK).body(report);
  }

  @PostMapping
  public ResponseEntity<ReportDTOResponse> createReport(
    @RequestBody ReportDTORequest reportDTO,
    UriComponentsBuilder uriBuilder
  ) {
    ReportDTOResponse report = this.reportService.create(reportDTO);

    URI uri = uriBuilder.path("/denuncia/{id}").buildAndExpand(report.id()).toUri();

    return ResponseEntity.created(uri).body(report);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ReportDTOResponse> deleteReport(
    @PathVariable UUID id
  ) {
    ReportDTOResponse reportDeleted = this.reportService.delete(id);

    return ResponseEntity.status(HttpStatus.OK).body(reportDeleted);
  }
}
