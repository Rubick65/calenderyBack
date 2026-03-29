package com.rubenmartin.calenderyback.user.domain.entity;

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
    int cantidadSeguidores;
    int cantidadSeguidos;
    List<Rol> roles;
    boolean enable;
}
