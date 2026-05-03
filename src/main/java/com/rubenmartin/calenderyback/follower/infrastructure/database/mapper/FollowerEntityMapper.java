package com.rubenmartin.calenderyback.follower.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.follower.infrastructure.database.entity.FollowerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowerEntityMapper {

    FollowerEntity mapToFollowerEntity(Follower follower);

    @Mapping(target = "userFollow", ignore = true)
    @Mapping(target = "userFollowed", ignore = true)
    Follower mapToFollower(FollowerEntity followerEntity);
}
