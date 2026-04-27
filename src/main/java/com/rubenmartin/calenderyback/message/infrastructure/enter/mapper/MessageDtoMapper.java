package com.rubenmartin.calenderyback.message.infrastructure.enter.mapper;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.infrastructure.enter.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageDtoMapper {

    @Mapping(source = "fromUser", target = "fromUser.idUsuario")
    @Mapping(source = "toUser", target = "toUser.idUsuario")
    @Mapping(source = "chatId", target = "chatId.id")
    Message mapToMessage(MessageDto messageDto);

    @Mapping(source = "fromUser.idUsuario", target = "fromUser")
    @Mapping(source = "toUser.idUsuario", target = "toUser")
    @Mapping(source = "chatId.id", target = "chatId")
    MessageDto mapToMessageDto(Message message);

}
