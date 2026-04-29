package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDataDto {
    private Long idPost;
    private Long idUsuario;
    private String message;
    private LocalDate calendarDate;
}
