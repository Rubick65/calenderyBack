package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PublicationProfileDto {
    private Long id;
    private String fotoPublicacion;
    private String mensasje;
    private int cantidadLikes;
    private int cantidadComentarios;
    private LocalDate fechaCalendario;
    private LocalDateTime fechaPublicacion;
}
