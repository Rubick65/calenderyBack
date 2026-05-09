package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponseDto {

    @NotBlank
    private String nombre;
    private String fotoPerfil;
    private String descripcion;
    private int cantidadSeguidores;
    private int cantidadSeguidos;
    private boolean seguidor;
    private boolean existeChat;
}
