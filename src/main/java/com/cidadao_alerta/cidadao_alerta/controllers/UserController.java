package com.cidadao_alerta.cidadao_alerta.controllers;


import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import com.cidadao_alerta.cidadao_alerta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    List<UserDTOResponse> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    UserEntity createNewUser(@RequestBody UserDTORequest userDTO) {
        return userService.createNewUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    UserDTOResponse getUserById(@PathVariable UUID id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado!");
        }
    }
}
