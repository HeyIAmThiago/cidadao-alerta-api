package com.cidadao_alerta.cidadao_alerta.models.dtos;

import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {
    private UUID id;
    private String name;
    private String email;
    private Role role;


}
