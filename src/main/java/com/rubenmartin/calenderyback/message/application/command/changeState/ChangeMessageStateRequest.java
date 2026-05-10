package com.rubenmartin.calenderyback.message.application.command.changeState;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeMessageStateRequest implements Request<Void> {
    private Long messageId;
    private EstadoMensaje messageState;
}
