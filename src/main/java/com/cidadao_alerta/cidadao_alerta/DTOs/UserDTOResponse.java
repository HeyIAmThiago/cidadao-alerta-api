package com.cidadao_alerta.cidadao_alerta.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTOResponse {

    private int id;
    private String name;
    private String email;
    private String role;

    public UserDTOResponse() {

    }





    public UserDTOResponse(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
