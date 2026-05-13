package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto;

import lombok.Data;

@Data
public class UserChatDataDto {
    private Long idUsuario;
    private String nombre;
    private String fotoPerfil;
    private Long idChat;
    private String ultimoMensaje;
}
