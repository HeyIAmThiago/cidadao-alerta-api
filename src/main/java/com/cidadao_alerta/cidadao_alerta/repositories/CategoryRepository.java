package com.cidadao_alerta.cidadao_alerta.repositories;

import com.cidadao_alerta.cidadao_alerta.models.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
