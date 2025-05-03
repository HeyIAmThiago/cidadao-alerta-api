package com.cidadao_alerta.cidadao_alerta.services;

import com.cidadao_alerta.cidadao_alerta.entities.UsuarioEntity;
import com.cidadao_alerta.cidadao_alerta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UserRepository userRepository;


    public List<UsuarioEntity> getAllUsers() {
        return userRepository.findAll();
    }


}
