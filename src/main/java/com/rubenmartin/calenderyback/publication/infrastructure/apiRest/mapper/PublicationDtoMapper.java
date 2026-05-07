package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlResponse;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationHomeDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationProfileDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.SignedUrlPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PublicationDtoMapper {

    SignedUrlPostDto mapToSignedPostDto(GetPostPublicationSignedUrlResponse response);

    @Mapping(target = "fechaCalendario", source = "publicationDate.calendarDate")
    @Mapping(target = "fechaPublicacion", source = "publicationDate.uploadDate")
    @Mapping(target = "fotoPublicacion", source = "publicationFileName")
    @Mapping(target = "mensaje", source = "message")
    @Mapping(target = "cantidadLikes", source = "likesAmount")
    @Mapping(target = "cantidadComentarios", source = "commentaryAmount")
    PublicationProfileDto mapToPublicationProfile(Publication publication);

    @Mapping(target = "publicationData", source = "publication")
    @Mapping(target = "idUsuario", source = "user.idUsuario")
    @Mapping(target = "fotoPerfil", source = "user.fotoPerfil")
    @Mapping(target = "nombrePerfil", source = "user.nombre")
    PublicationHomeDto mapToPublicationHome(Publication publication);
}
