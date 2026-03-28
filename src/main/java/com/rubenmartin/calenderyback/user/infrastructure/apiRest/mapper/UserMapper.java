package com.rubenmartin.calenderyback.user.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.user.application.command.create.CreateUserRequest;
import com.rubenmartin.calenderyback.user.application.command.update.UpdateUserRequest;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    CreateUserRequest mapToCreateUserRequest(UserDto user);

    UpdateUserRequest mapToUpdateUserRequest(UserDto user);

    UserDto mapToUserDto(User user);
}
