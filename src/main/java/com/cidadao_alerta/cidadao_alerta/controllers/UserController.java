package com.cidadao_alerta.cidadao_alerta.controllers;


import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTORequest;
import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import com.cidadao_alerta.cidadao_alerta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
