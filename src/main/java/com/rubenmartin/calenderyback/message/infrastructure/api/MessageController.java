package com.rubenmartin.calenderyback.message.infrastructure.api;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.message.application.command.save.SaveMessageRequest;
import com.rubenmartin.calenderyback.message.application.command.save.SaveMessageResponse;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageDto;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final Mediator mediator;
    private final MessageDtoMapper messageDtoMapper;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @MessageMapping("/secured/chat")
    @SendTo("/secured/history")
    public MessageDto sendMessage(MessageDto messageDto) {
        LocalDateTime date = LocalDateTime.now();
        SaveMessageRequest request = new SaveMessageRequest(messageDtoMapper.mapToMessage(messageDto), date);
        SaveMessageResponse response = mediator.dispatch(request);

        return messageDtoMapper.mapToMessageDto(response.getMessage());
    }

    @Override
    @MessageMapping("/chat.sendPrivate")
    public void sendSpecific(@Payload MessageDto messageDto, Principal user) {
        LocalDateTime date = LocalDateTime.now();
        SaveMessageRequest request = new SaveMessageRequest(messageDtoMapper.mapToMessage(messageDto), date);
        SaveMessageResponse response = mediator.dispatch(request);
        Message message = response.getMessage();

        simpMessagingTemplate.convertAndSendToUser(
                message.getToUser().getEmail(),
                "/queue/mensajes",
                messageDtoMapper.mapToMessageResponseChat(message, messageDto.getChatId())
        );
    }


}
