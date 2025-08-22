package com.cidadao_alerta.cidadao_alerta.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cidadao_alerta.cidadao_alerta.models.dtos.CategoryDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.dtos.ReportDTOUpdate;
import com.cidadao_alerta.cidadao_alerta.models.entities.ReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.entities.StatusReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;
import com.cidadao_alerta.cidadao_alerta.models.mappers.CategoryMapper;
import com.cidadao_alerta.cidadao_alerta.models.mappers.ReportMapper;
import com.cidadao_alerta.cidadao_alerta.repositories.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final ReportRepository reportRepository;
  private final StatusReportService statusReportService;
  
  public List<ReportDTOResponse> getAllReports() {
    List<ReportEntity> reports = this.reportRepository.findAll();
    List<ReportDTOResponse> reportsDTO = reports.stream().map(
      report -> new ReportDTOResponse(
        report,
        CategoryMapper.listEntityToDto(report.getCategories())
      )
    ).toList();

    return reportsDTO;
  }

  public Page<ReportDTOResponse> getReports(Pageable pageable) {
    Page<ReportEntity> reports = this.reportRepository.findAll(pageable);
    Page<ReportDTOResponse> reportsDTO = reports.map(
      report -> new ReportDTOResponse(
        report, 
        CategoryMapper.listEntityToDto(report.getCategories())
      )
    );

    return reportsDTO;
  }

  public ReportDTOResponse getReportById(UUID id) {
    ReportEntity report = this.getReportEntity(id);

    List<CategoryDTOResponse> categoriesDTO = CategoryMapper.listEntityToDto(report.getCategories());

    return new ReportDTOResponse(report, categoriesDTO);
  }

  public ReportEntity getReportEntity(UUID id) {
    ReportEntity report = this.reportRepository.findById(id).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Denuncia não encontrada, verifique o id.")
    );

    return report;
  }

  public ReportDTOResponse create(ReportDTORequest reportDTO) {
    final StatusReportEntity statusInicial = this.statusReportService.findStatus(StatusReportEnum.PENDENTE);
    ReportEntity report = ReportMapper.dtoToEntity(reportDTO, statusInicial);

    report = this.reportRepository.save(report);

    List<CategoryDTOResponse> categoriesDTO = CategoryMapper.listEntityToDto(report.getCategories());

    return new ReportDTOResponse(report, categoriesDTO);
  }

  public List<ReportDTOResponse> createAll(List<ReportDTORequest> reportsDTO) {
    final StatusReportEntity statusInicial = this.statusReportService.findStatus(StatusReportEnum.PENDENTE);
    List<ReportEntity> reports = reportsDTO.stream().map(
      report -> ReportMapper.dtoToEntity(report, statusInicial)
    ).toList();

    reports = this.reportRepository.saveAll(reports);

    List<ReportDTOResponse> reportsDTOResponse = reports.stream().map(
      report -> new ReportDTOResponse(
        report,
        CategoryMapper.listEntityToDto(report.getCategories())
      )
    ).toList();

    return reportsDTOResponse;
  }

  public ReportDTOResponse delete(UUID id) {
    ReportEntity report = this.getReportEntity(id);

    this.reportRepository.delete(report);
    List<CategoryDTOResponse> categoriesDTO = CategoryMapper.listEntityToDto(report.getCategories());

    return new ReportDTOResponse(report, categoriesDTO);
  }

  public ReportDTOResponse editReport(UUID id, ReportDTOUpdate editReportDto) {
    ReportEntity report = this.getReportEntity(id);

    report.setTitle(editReportDto.title());
    report.setDescription(editReportDto.description());

    List<CategoryDTOResponse> categoriesDTO = CategoryMapper.listEntityToDto(report.getCategories());

    return new ReportDTOResponse(report, categoriesDTO);
  }
}
