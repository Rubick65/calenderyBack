package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto;

import lombok.Data;

@Data
public class PublicationHomeDto {
    private PublicationProfileDto publicationData;
    private Long idUsuario;
    private String fotoPerfil;
    private String nombrePerfil;
}
