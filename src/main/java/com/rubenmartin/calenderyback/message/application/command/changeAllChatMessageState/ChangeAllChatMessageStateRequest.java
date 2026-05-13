package com.rubenmartin.calenderyback.message.application.command.changeAllChatMessageState;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeAllChatMessageStateRequest implements Request<Void> {
    private Long chatId;
    private Long userId;
    private EstadoMensaje estadoMensaje;
}
