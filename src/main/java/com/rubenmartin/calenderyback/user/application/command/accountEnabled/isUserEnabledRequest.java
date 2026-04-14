package com.rubenmartin.calenderyback.user.application.command.accountEnabled;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class isUserEnabledRequest implements Request<Void> {
    Long idUsuario;
}
