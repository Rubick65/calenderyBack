package com.rubenmartin.calenderyback.user.application.command.update;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequest implements Request<Void> {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String descripcion;
    private String keypass;
    private String fotoPerfil;
    private String clavePublica;
    private int cantidadSeguidores;
    private int cantidadSeguidos;
    private boolean enable;
    private List<Rol> roles;
}
