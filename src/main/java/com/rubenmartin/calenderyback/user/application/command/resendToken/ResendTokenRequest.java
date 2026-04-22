package com.rubenmartin.calenderyback.user.application.command.resendToken;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResendTokenRequest implements Request<Void> {
    private Long idUsuario;
}
