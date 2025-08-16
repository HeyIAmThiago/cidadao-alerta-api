package com.cidadao_alerta.cidadao_alerta.controllers;

import com.cidadao_alerta.cidadao_alerta.models.entities.CategoryEntity;
import com.cidadao_alerta.cidadao_alerta.repositories.CategoryRepository;
import com.cidadao_alerta.cidadao_alerta.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    List<CategoryEntity> findAll() {
        return categoryService.findAll();
    }

}
