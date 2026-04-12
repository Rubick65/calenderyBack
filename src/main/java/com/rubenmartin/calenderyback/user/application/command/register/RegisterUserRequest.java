package com.rubenmartin.calenderyback.user.application.command.register;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.Data;

/**
 * Gestiona la petición de creación de un usuario inicialmente
 *
 */
@Data
public class RegisterUserRequest implements Request<RegisterUserResponse> {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String keypass;
    private String clavePublica;
}
