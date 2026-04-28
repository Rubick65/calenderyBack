package com.rubenmartin.calenderyback.publicationDate.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.publicationDate.domain.entity.PublicationDate;
import com.rubenmartin.calenderyback.publicationDate.infrastructure.database.entity.PublicationDateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PublicationDateEntityMapper {

    PublicationDateEntity mapToPublicationDateEntity(PublicationDate publicationDate);

    PublicationDate mapToPublicationDateEntity(PublicationDateEntity publication);
}
