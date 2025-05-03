package com.cidadao_alerta.cidadao_alerta.repositories;


import com.cidadao_alerta.cidadao_alerta.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {}
