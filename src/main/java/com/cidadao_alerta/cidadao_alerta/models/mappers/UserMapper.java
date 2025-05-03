package com.cidadao_alerta.cidadao_alerta.models.mappers;

import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserMapper(){}

    public UserDTOResponse toDTO(UserEntity userEntity) {
        UserDTOResponse dto = new UserDTOResponse();
        dto.setEmail(userEntity.getEmail());
        dto.setId(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setRole(userEntity.getRole());
        return dto;

    }


}
