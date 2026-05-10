package com.rubenmartin.calenderyback.message.application.command.changeState;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.domain.exception.MessageStateNoUpdatedException;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeMessageStateHandler implements RequestHandler<ChangeMessageStateRequest, Void> {
    private final MessageRepositoryPort messageRepositoryPort;

    @Override
    public Void handle(ChangeMessageStateRequest request) {
        Long messageId = request.getMessageId();
        EstadoMensaje messageState = request.getMessageState();

        int responseCode = messageRepositoryPort.changeMessageState(messageId, messageState);
        
        if (responseCode == 0)
            throw new MessageStateNoUpdatedException(messageId);

        return null;
    }

    @Override
    public Class<ChangeMessageStateRequest> getRequestType() {
        return ChangeMessageStateRequest.class;
    }
}
