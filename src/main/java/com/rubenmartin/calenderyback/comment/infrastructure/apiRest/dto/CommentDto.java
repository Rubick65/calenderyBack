package com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    
    private Long idUsuario;

    @NotBlank
    private Long idPublicacion;

    private Long idComentario;

    private String nombreUsuario;

    private String fotoUsuario;

    @NotBlank
    @Length(min = 1, max = 300)
    private String comentario;

    private LocalDateTime localDateTimeData;
}
