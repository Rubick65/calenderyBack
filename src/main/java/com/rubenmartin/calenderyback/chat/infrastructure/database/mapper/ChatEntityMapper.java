package com.rubenmartin.calenderyback.chat.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.infrastructure.database.entity.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ChatEntityMapper {
    ChatEntity mapToChatEntity(Chat chat);

    Chat mapToChat(ChatEntity chatEntity);
}
