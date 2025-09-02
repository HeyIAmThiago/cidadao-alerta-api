package com.cidadao_alerta.cidadao_alerta.repositories;


import com.cidadao_alerta.cidadao_alerta.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  
  Optional<UserEntity> findByEmail(String email);
}
