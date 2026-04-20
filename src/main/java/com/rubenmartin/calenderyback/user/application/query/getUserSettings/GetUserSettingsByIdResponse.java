package com.rubenmartin.calenderyback.user.application.query.getUserSettings;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserSettingsByIdResponse {
    private String nombre;
    private String fotoPerfil;
    private String descripcion;
}
