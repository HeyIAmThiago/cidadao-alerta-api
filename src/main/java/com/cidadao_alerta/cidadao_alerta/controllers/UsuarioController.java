package com.cidadao_alerta.cidadao_alerta.controllers;


import com.cidadao_alerta.cidadao_alerta.entities.UsuarioEntity;
import com.cidadao_alerta.cidadao_alerta.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping
    List<UsuarioEntity> getAllUsers() {


        return usuarioService.getAllUsers();


    }
}
