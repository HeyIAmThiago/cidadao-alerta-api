package com.cidadao_alerta.cidadao_alerta.models.entities;

import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="user_app")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;


}
