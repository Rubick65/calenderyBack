package com.rubenmartin.calenderyback.publicationLike.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.publication.infrastructure.database.mapper.PublicationEntityMapper;
import com.rubenmartin.calenderyback.publicationLike.domain.entity.PublicationLike;
import com.rubenmartin.calenderyback.publicationLike.infrastructure.database.entity.PublicationLikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {PublicationEntityMapper.class, FollowerEntityMapper.class})
public interface PublicationLikeEntityMapper {

    PublicationLikeEntity mapToPublicationLikeEntity(PublicationLike publicationLike);

    PublicationLike mapToPublicationLike(PublicationLikeEntity publicationLikeEntity);
}
