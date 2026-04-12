package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto;

import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import com.rubenmartin.calenderyback.user.infrastructure.validation.email.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class UserDto {

    Long idUsuario;
    @NotBlank
    String nombre;
    @NotBlank
    @ValidEmail
    String email;
    @Size(min = 1, max = 100, message = "User description length should be lower than 100 characters.")
    String descripcion;
    @NotBlank
    String keypass;
    String fotoPerfil;
    @NotBlank
    String clavePublica;
    int cantidadSeguidores;
    int cantidadSeguidos;
    boolean enable;
    List<RolEntity> roles;
}
