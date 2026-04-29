package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlResponse;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
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
    PublicationProfileDto mapToPublicationProfile(Publication publication);
}
