package com.rubenmartin.calenderyback.publication.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {FollowerEntityMapper.class})
public interface PublicationEntityMapper {

    @Mapping(target = "publicationDate.publication", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = {"like"})
    PublicationEntity mapToPublicationEntity(Publication publication);

    @Mapping(target = "publicationDate.publication", ignore = true)
    @Mapping(target = "like", ignore = true)
    Publication mapToPublication(PublicationEntity publicationEntity);
}
