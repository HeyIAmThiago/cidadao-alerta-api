package com.cidadao_alerta.cidadao_alerta.services;

import com.cidadao_alerta.cidadao_alerta.models.entities.CategoryEntity;
import com.cidadao_alerta.cidadao_alerta.repositories.CategoryRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<CategoryEntity> findAll() {
       return categoryRepository.findAll();
    }
}
