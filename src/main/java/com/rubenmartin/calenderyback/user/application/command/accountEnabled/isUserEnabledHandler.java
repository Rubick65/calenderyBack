package com.rubenmartin.calenderyback.user.application.command.accountEnabled;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class isUserEnabledHandler implements RequestHandler<IsUserEnabledRequest, IsUserEnabledResponse> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public IsUserEnabledResponse handle(IsUserEnabledRequest request) {
        Long idUsuario = request.getIdUsuario();
        boolean isActive = userRepositoryPort.accountIsEnabled(idUsuario);

        return new IsUserEnabledResponse(isActive);
    }

    @Override
    public Class<IsUserEnabledRequest> getRequestType() {
        return IsUserEnabledRequest.class;
    }
}
