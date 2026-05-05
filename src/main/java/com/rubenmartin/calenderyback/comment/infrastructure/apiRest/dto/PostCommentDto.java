package com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto;

import lombok.Data;

@Data
public class PostCommentDto {
    private Long idPublicacion;
    private String comentario;
}
