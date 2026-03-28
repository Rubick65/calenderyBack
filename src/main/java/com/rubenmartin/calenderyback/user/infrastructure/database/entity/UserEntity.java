package com.rubenmartin.calenderyback.user.infrastructure.database.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(nullable = false, unique = true)
    String clavePublica;

    @Column(nullable = true)
    int cantidadSeguidores;

    @Column(nullable = true)
    int cantidadSeguidos;
    
    List<String> roles;

}
