package com.rubenmartin.calenderyback.chat.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatDtoMapper {

    @Mapping(source = "user1.idUsuario", target = "user1")
    @Mapping(source = "user2.idUsuario", target = "user2")
    ChatDto mapToChatDto(Chat chat);

    @Mapping(source = "user1", target = "user1.idUsuario")
    @Mapping(source = "user2", target = "user2.idUsuario")
    Chat mapToChat(ChatDto chatDto);
}
