package com.cidadao_alerta.cidadao_alerta.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nome")
    private String nome;

    @Column(name="senha")
    private String senha;

    @Column(name="email")
    private String email;


}
