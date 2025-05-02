package com.cidadao_alerta.cidadao_alerta.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping
    String getAllUsers() {
        return "Bem-vindo!";
    }
}
