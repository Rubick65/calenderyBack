package com.rubenmartin.calenderyback.user.application.command.accountEnabled;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserDisableAccountException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class isUserEnabledHandler implements RequestHandler<isUserEnabledRequest, Void> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(isUserEnabledRequest request) {
        Long idUsuario = request.getIdUsuario();
        boolean isActive = userRepositoryPort.accountIsEnabled(idUsuario);

        if (!isActive)
            throw new UserDisableAccountException(idUsuario);

        return null;
    }

    @Override
    public Class<isUserEnabledRequest> getRequestType() {
        return isUserEnabledRequest.class;
    }
}
