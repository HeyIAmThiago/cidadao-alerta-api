package com.cidadao_alerta.cidadao_alerta.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name="comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="text")
    private String text;

    @Column(name="upvotes")
    private Long upvotes;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "created_at", column = @Column(name = "created_at")),
            @AttributeOverride( name = "updated_at", column = @Column(name = "updated_at"))
    })
    AuditInfo temporalInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportEntity report;

}
