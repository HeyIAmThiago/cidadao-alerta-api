package com.cidadao_alerta.cidadao_alerta.models.entities;

import java.util.List;
import java.util.UUID;

import com.cidadao_alerta.cidadao_alerta.models.enums.StatusReportEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "status_report")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class StatusReportEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusReportEnum status;

  @OneToMany(mappedBy = "status")
  private List<ReportEntity> reports;
}
