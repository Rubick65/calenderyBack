package com.rubenmartin.calenderyback.message.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageDto;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageResponseDto;
import com.rubenmartin.calenderyback.user.infrastructure.database.mapper.UserEntityMapper;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserEntityMapper.class})
public interface MessageDtoMapper {

    @Mapping(source = "fromUser", target = "fromUser.idUsuario")
    @Mapping(source = "toUser", target = "toUser.idUsuario")
    @Mapping(source = "chatId", target = "chatId.id")
    Message mapToMessage(MessageDto messageDto);

    @Mapping(source = "fromUser.idUsuario", target = "fromUser")
    @Mapping(source = "toUser.idUsuario", target = "toUser")
    @Mapping(source = "chatId.id", target = "chatId")
    MessageDto mapToMessageDto(Message message);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idMensaje", source = "id")
    @Mapping(target = "idChat", source = "chatId.id")
    @Mapping(target = "timeStamp", source = "timeStamp")
    @Mapping(target = "estadoMensaje", source = "messageState")
    @Mapping(target = "contenido",
            expression = "java(isFromMe(message, currentUserId) ? message.getSelfMessage() : message.getContent())")
    @Mapping(target = "idUsuario", source = "fromUser.idUsuario")
    MessageResponseDto mapToMessageResponse(Message message, @Context Long currentUserId);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idMensaje", source = "message.id")
    @Mapping(target = "idChat", source = "chatId")
    @Mapping(target = "timeStamp", source = "message.timeStamp")
    @Mapping(target = "estadoMensaje", source = "message.messageState")
    @Mapping(target = "contenido", source = "message.content")
    MessageResponseDto mapToMessageResponseChat(Message message, Long chatId);

    default boolean isFromMe(Message message, Long currentUserId) {
        return message.getFromUser().getIdUsuario().equals(currentUserId);
    }

}
