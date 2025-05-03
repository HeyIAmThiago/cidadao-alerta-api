package com.cidadao_alerta.cidadao_alerta.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOResponse {

    private int id;
    private String nome;
    private String email;

    public UsuarioDTOResponse() {}





    public UsuarioDTOResponse(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
