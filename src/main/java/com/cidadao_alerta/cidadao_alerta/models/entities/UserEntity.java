    package com.cidadao_alerta.cidadao_alerta.models.entities;

    import com.cidadao_alerta.cidadao_alerta.models.enums.Role;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Table;
    import jakarta.persistence.Id;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.CascadeType;
    import lombok.Getter;
    import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
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

        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<CommentEntity> comments = new HashSet<>();
    }
