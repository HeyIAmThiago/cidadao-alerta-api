package com.cidadao_alerta.cidadao_alerta.services;

import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import com.cidadao_alerta.cidadao_alerta.models.mappers.UserMapper;
import com.cidadao_alerta.cidadao_alerta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    public List<UserDTOResponse> getAllUsers() {
        ArrayList<UserDTOResponse> usersResponse= new ArrayList<>();
        ArrayList<UserEntity> usuarios = new ArrayList<>(userRepository.findAll());


        for (UserEntity usuario : usuarios)
            usersResponse.add(userMapper.toDTO(usuario));


        return usersResponse;
    }

    public void createUser() {

    }
}
