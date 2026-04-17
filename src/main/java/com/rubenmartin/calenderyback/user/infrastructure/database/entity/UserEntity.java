package com.rubenmartin.calenderyback.user.infrastructure.database.entity;

import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUsuario;

    @Column(nullable = false, unique = true)
    String nombre;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = true)
    String descripcion;

    @Column(unique = true, nullable = false)
    String keypass;

    @Column(nullable = true)
    String fotoPerfil;

    @Column(nullable = true, unique = true, columnDefinition = "TEXT")
    String clavePublica;

    @Column(nullable = true)
    int cantidadSeguidores;

    @Column(nullable = true)
    int cantidadSeguidos;

    @Column(nullable = false)
    boolean enable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    List<RolEntity> roles;

}
