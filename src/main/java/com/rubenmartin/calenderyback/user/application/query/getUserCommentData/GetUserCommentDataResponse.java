package com.rubenmartin.calenderyback.user.application.query.getUserCommentData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserCommentDataResponse {
    private String nombreUsuario;
    private String fotoPerfil;
}
