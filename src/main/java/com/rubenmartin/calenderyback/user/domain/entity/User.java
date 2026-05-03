package com.rubenmartin.calenderyback.user.domain.entity;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Entidad usuario
 */
public class User {
    // Atributos comunes a todos los usuarios
    Long idUsuario;
    String nombre;
    String email;
    String descripcion;
    String keypass;
    String fotoPerfil;
    String clavePublica;
    List<Rol> roles;
    List<Follower> followers;
    List<Follower> followings;
    boolean enable;
}
