package com.cidadao_alerta.cidadao_alerta.models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
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

  @ManyToMany
  @JoinTable(
    name = "report_category",
    joinColumns = @JoinColumn(name = "report_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  @Builder.Default
  private List<CategoryEntity> categories = new ArrayList<>();

  @Column(name = "image_url")
  private String imageUrl;

  @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<CommentEntity> comments;

  @ManyToOne
  @JoinColumn(name = "status_report_id")
  private StatusReportEntity status;
}
