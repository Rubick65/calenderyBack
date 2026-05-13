package com.rubenmartin.calenderyback.message.application.command.changeAllChatMessageState;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeAllChatMessageStateHandler implements RequestHandler<ChangeAllChatMessageStateRequest, Void> {
    private final MessageRepositoryPort messageRepositoryPort;

    @Override
    public Void handle(ChangeAllChatMessageStateRequest request) {
        Long userId = request.getUserId();
        Long chatId = request.getChatId();
        EstadoMensaje messageState = request.getEstadoMensaje();

        messageRepositoryPort.changeAllChatMessagesState(userId, chatId, messageState);

        return null;
    }

    @Override
    public Class<ChangeAllChatMessageStateRequest> getRequestType() {
        return ChangeAllChatMessageStateRequest.class;
    }
}
