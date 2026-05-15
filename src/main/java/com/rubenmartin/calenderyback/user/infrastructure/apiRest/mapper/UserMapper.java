package com.rubenmartin.calenderyback.user.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.message.domain.model.LastMessageDataModel;
import com.rubenmartin.calenderyback.user.application.command.register.RegisterUserRequest;
import com.rubenmartin.calenderyback.user.application.command.update.UpdateUserRequest;
import com.rubenmartin.calenderyback.user.application.command.updateUserSettings.UpdateUserSettingsRequest;
import com.rubenmartin.calenderyback.user.application.query.getUserProfile.GetUserProfileByIdResponse;
import com.rubenmartin.calenderyback.user.application.query.getUserSettings.GetUserSettingsByIdResponse;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserChatDataDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserProfileResponseDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserReducedData;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserSettingsResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    RegisterUserRequest mapToCreateUserRequest(UserDto user);

    UpdateUserRequest mapToUpdateUserRequest(UserDto user);

    @Mapping(target = "idUsuario", source = "userId")
    UpdateUserSettingsRequest mapToUpdateUserSettingsRequest(UserSettingsResponseDto user, Long userId);

    @Mapping(target = "cantidadSeguidores", ignore = true)
    @Mapping(target = "cantidadSeguidos", ignore = true)
    UserDto mapToUserDto(User user);

    UserSettingsResponseDto mapToUserSettingsResponseDto(GetUserSettingsByIdResponse response);

    @Mapping(target = "fotoPerfil", source = "fotoPerfil")
    @Mapping(target = "seguidor", ignore = true)
    @Mapping(target = "existeChat", ignore = true)
    UserProfileResponseDto mapToUserProfileResponseDto(GetUserProfileByIdResponse response, String fotoPerfil);


    @Mapping(target = "fotoPerfil", source = "fotoPerfil")
    UserSettingsResponseDto mapToUserSettingsResponseDto(GetUserSettingsByIdResponse response, String fotoPerfil);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "mensajeNuevo", source = "lastMessage.noRead")
    @Mapping(target = "ultimoMensaje", source = "lastMessage.contenido")
    @Mapping(target = "idChat", source = "lastMessage.idChat")
    @Mapping(target = "nombre", source = "user.nombre")
    @Mapping(target = "idUsuario", source = "user.idUsuario")
    @Mapping(target = "fotoPerfil", source = "user.fotoPerfil")
    UserChatDataDto mapToUserChatDto(User user, LastMessageDataModel lastMessage);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idUsuario", source = "idUsuario")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "fotoPerfil", source = "fotoPerfil")
    UserReducedData mapToReduceData(User user);
}
