package com.rubenmartin.calenderyback.follower.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.follower.infrastructure.database.entity.FollowerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FollowerEntityMapper {

    FollowerEntity mapToFollowerEntity(Follower follower);

    Follower mapToFollower(FollowerEntity followerEntity);
}
