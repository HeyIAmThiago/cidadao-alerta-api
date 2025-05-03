package com.cidadao_alerta.cidadao_alerta.controllers;


import com.cidadao_alerta.cidadao_alerta.models.dtos.UserDTOResponse;
import com.cidadao_alerta.cidadao_alerta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
