package com.cidadao_alerta.cidadao_alerta.services;

import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import com.cidadao_alerta.cidadao_alerta.models.mappers.UserMapper;
import com.cidadao_alerta.cidadao_alerta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public UserEntity createNewUser(UserDTORequest userDTO) {
        UserEntity entityToRegister = userMapper.toEntity(userDTO);

        return userRepository.save(entityToRegister);
    }

    public UserDTOResponse getUserById(UUID id) {
        var result = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado!")
        );

        return userMapper.toDTO(result);
    }

    public UserEntity delete(UUID id) {


        UserEntity userToDelete = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado!")
        );


        userRepository.delete(userToDelete);

        return userToDelete;








    }




}
