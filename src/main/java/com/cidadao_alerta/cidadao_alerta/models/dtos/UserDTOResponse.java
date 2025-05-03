package com.cidadao_alerta.cidadao_alerta.models.dtos;

import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {
    private int id;
    private String name;
    private String email;
    private Role role;


}
