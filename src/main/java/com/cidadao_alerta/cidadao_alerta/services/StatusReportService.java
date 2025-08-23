package com.cidadao_alerta.cidadao_alerta.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cidadao_alerta.cidadao_alerta.models.dtos.StatusReportDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.StatusReportDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.StatusReportEntity;
import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;
import com.cidadao_alerta.cidadao_alerta.repositories.StatusReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusReportService {
  
  private final StatusReportRepository statusReportRepository;

  public List<StatusReportEntity> findAll() {
    return statusReportRepository.findAll();
  }

  public List<StatusReportDTOResponse> findAllDTO() {
    return statusReportRepository.findAll().stream()
      .map(status -> new StatusReportDTOResponse(status)).toList();
  }

  public StatusReportEntity findStatus(UUID id) {
    return statusReportRepository.findById(id).orElseThrow(() -> notFound("Status não encontrado, verifique o id enviado."));
  }

  public StatusReportDTOResponse findStatusDTO(UUID id) {
    StatusReportEntity status = statusReportRepository.findById(id)
      .orElseThrow(() -> notFound("Status não encontrado, verifique o id enviado."));

    return new StatusReportDTOResponse(status);
  }

  public StatusReportEntity findStatus(StatusReportEnum statusEnum) {
    return statusReportRepository.findByStatus(statusEnum).orElseThrow(() -> notFound("Status não encontrado, verifique o status enviado."));
  }

  private ResponseStatusException notFound(String message) {
    return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
  }

  public StatusReportDTOResponse createStatus(StatusReportDTORequest statusReportDTORequest) {
    this.statusReportRepository.findByStatus(statusReportDTORequest.status()).ifPresent(status -> {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status já cadastrado.");
    });

    StatusReportEntity status = new StatusReportEntity();
    status.setStatus(statusReportDTORequest.status());

    StatusReportEntity savedStatus = statusReportRepository.save(status);

    return new StatusReportDTOResponse(savedStatus);
  }
}
