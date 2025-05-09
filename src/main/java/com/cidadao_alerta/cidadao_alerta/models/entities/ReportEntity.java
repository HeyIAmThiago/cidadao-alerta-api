package com.cidadao_alerta.cidadao_alerta.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;
  private String description;

  @Column(name = "image_url")
  private String imageUrl;
}
