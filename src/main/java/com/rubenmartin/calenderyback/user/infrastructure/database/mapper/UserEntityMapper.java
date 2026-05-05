package com.rubenmartin.calenderyback.user.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {FollowerEntityMapper.class})
public interface UserEntityMapper {

    // Desde entidad de base de datos a entidad usuario
    UserEntity mapToUserEntity(User user);

    // Desde entidad usuario a entidad de base de datos
    User mapToUser(UserEntity userEntity);

}
