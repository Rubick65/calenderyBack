package com.rubenmartin.calenderyback.message.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.model.LastMessageDataModel;
import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import com.rubenmartin.calenderyback.message.infrastructure.projection.LastMessageDataProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {FollowerEntityMapper.class})
public interface MessageEntityMapper {

    MessageEntity mapToMessageEntity(Message message);

    Message mapToMessage(MessageEntity messageEntity);

    @Mapping(target = "contenido", source = "contenido")
    @Mapping(target = "idChat", source = "chatId")
    LastMessageDataModel mapToLastMessageModel(LastMessageDataProjection lastMessage);
}
