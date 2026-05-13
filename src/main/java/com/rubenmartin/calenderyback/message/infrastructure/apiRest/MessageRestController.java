package com.rubenmartin.calenderyback.message.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.message.application.command.changeAllChatMessageState.ChangeAllChatMessageStateRequest;
import com.rubenmartin.calenderyback.message.application.command.changeState.ChangeMessageStateRequest;
import com.rubenmartin.calenderyback.message.application.command.checkForPendingMessages.CheckForPendingMessagesRequest;
import com.rubenmartin.calenderyback.message.application.query.getChatMessages.GetChatMessagesByIdRequest;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageResponseDto;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageRestController implements MessageRestApi {
    private final Mediator mediator;
    private final MessageDtoMapper messageDtoMapper;

    @Override
    //@PreAuthorize("#currentUserId == authentication.principal.idUsuario")
    @GetMapping("/app/getChatMessages")
    public ResponseEntity<Page<MessageResponseDto>> getMessages(@RequestParam("idChat") Long idChat, @RequestParam("usuarioActual") Long currentUserId, Pageable pageable) {
        GetChatMessagesByIdRequest getChatMessages = new GetChatMessagesByIdRequest(idChat, pageable);
        Page<MessageResponseDto> messagePage = mediator.dispatch(getChatMessages).getMessagePage().map(message -> messageDtoMapper.mapToMessageResponse(message, currentUserId));

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

    @Override
    @PutMapping("/app/cahngeAllChatMessgesToReadedState")
    public ResponseEntity<Void> changeAllChatMessageToReadedState(@Param("idUsuario") Long userId, @Param("idChat") Long chatId) {
        ChangeAllChatMessageStateRequest changeAllChatMessageStateRequest = new ChangeAllChatMessageStateRequest(chatId, userId, EstadoMensaje.LEIDO);
        mediator.dispatch(changeAllChatMessageStateRequest);
        
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/app/checkForPendingMessages")
    public ResponseEntity<Boolean> checkForPendingMessages(Authentication auth) {
        CheckForPendingMessagesRequest checkForPendingMessagesRequest = new CheckForPendingMessagesRequest(auth.getName());
        boolean pendingMessages = mediator.dispatch(checkForPendingMessagesRequest).isPendingMessages();

        return ResponseEntity.ok(pendingMessages);
    }
}
