package com.rubenmartin.calenderyback.message.infrastructure.enter;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.message.application.command.save.SaveMessageRequest;
import com.rubenmartin.calenderyback.message.application.query.getMessage.GetMessageByIdRequest;
import com.rubenmartin.calenderyback.message.application.query.getMessage.GetMessageByIdResponse;
import com.rubenmartin.calenderyback.message.infrastructure.enter.dto.MessageDto;
import com.rubenmartin.calenderyback.message.infrastructure.enter.mapper.MessageDtoMapper;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class MessageEnterPoint implements MessageEnterInterface {

    private MessageDtoMapper messageDtoMapper;
    private Mediator mediator;

    @Override
    public void saveMessage(MessageDto message) {
        LocalDateTime date = LocalDateTime.now();
        SaveMessageRequest saveRequest = new SaveMessageRequest(messageDtoMapper.mapToMessage(message), date);
        mediator.dispatch(saveRequest);
    }

    @Override
    public MessageDto getMessage(Long id) {
        GetMessageByIdRequest getMessageRequest = new GetMessageByIdRequest(id);
        GetMessageByIdResponse getMessageByIdResponse = mediator.dispatch(getMessageRequest);

        return messageDtoMapper.mapToMessageDto(getMessageByIdResponse.getMessage());
    }
}
