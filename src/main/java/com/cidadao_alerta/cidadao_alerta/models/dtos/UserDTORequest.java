package com.cidadao_alerta.cidadao_alerta.models.dtos;


import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORequest {

    Role role;
    String name;
    String email;
    String password;


}
