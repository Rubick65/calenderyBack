package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReducedData {
    private Long idUsuario;
    private String nombre;
    private String fotoPefil;
}
