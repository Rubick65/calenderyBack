package com.rubenmartin.calenderyback.user.application.command.create;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.Data;

/**
 * Gestiona la petición de creación de un usuario inicialmente
 *
 */
@Data
public class CreateUserRequest implements Request<Void> {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String keypass;
    private String clavePublica;
}
