package com.cidadao_alerta.cidadao_alerta.models.dtos;


import com.cidadao_alerta.cidadao_alerta.models.enums.Role;

public record UserDTORequest(
        Role role,
        String name,
        String email,
        String password
) {
    
}