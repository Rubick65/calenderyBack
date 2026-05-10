package com.rubenmartin.calenderyback.message.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.message.application.command.changeState.ChangeMessageStateRequest;
import com.rubenmartin.calenderyback.message.application.query.getChatMessages.GetChatMessagesByIdRequest;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageDto;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageRestController implements MessageRestApi {
    private final Mediator mediator;
    private final MessageDtoMapper messageDtoMapper;

    @Override
    @GetMapping("/app/getChatMessages")
    public ResponseEntity<Page<MessageDto>> getMessages(@RequestParam("idChat") Long idChat, Pageable pageable) {
        GetChatMessagesByIdRequest getChatMessages = new GetChatMessagesByIdRequest(idChat, pageable);
        Page<MessageDto> messagePage = mediator.dispatch(getChatMessages).getMessagePage().map(messageDtoMapper::mapToMessageDto);

        return ResponseEntity.ok(messagePage);
    }

    @Override
    @PutMapping("/app/changeMessageToDeliveredSate")
    public ResponseEntity<Void> changeMessageToSendState(@RequestParam("idMensaje") Long messageId) {
        EstadoMensaje deliveredState = EstadoMensaje.ENTREGADO;
        ChangeMessageStateRequest changeMessageStateRequest = new ChangeMessageStateRequest(messageId, deliveredState);
        mediator.dispatch(changeMessageStateRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/app/changeMessageToReadedState")
    public ResponseEntity<Void> changeMessageToReadedState(@RequestParam("idMensaje") Long messageId) {
        EstadoMensaje readState = EstadoMensaje.LEIDO;
        ChangeMessageStateRequest changeMessageStateRequest = new ChangeMessageStateRequest(messageId, readState);
        mediator.dispatch(changeMessageStateRequest);
        
        return ResponseEntity.ok().build();
    }
}
