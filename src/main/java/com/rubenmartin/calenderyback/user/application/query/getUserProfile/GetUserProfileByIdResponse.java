package com.rubenmartin.calenderyback.user.application.query.getUserProfile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserProfileByIdResponse {
    private String nombre;
    private String fotoPerfil;
    private String descripcion;
    private int cantidadSeguidores;
    private int cantidadSeguidos;
}
