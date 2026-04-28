package com.rubenmartin.calenderyback.publication.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PublicationEntityMapper {

    PublicationEntity mapToPublicationEntity(Publication publication);

    Publication mapToPublication(PublicationEntity publicationEntity);
}
