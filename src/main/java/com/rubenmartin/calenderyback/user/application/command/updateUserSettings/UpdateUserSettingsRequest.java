package com.rubenmartin.calenderyback.user.application.command.updateUserSettings;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.Data;

@Data
public class UpdateUserSettingsRequest implements Request<Void> {
    private Long idUsuario;
    private String nombre;
    private String fotoPerfil;
    private String descripcion;
}
