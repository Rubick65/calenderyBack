package com.rubenmartin.calenderyback.user.application.query.getIDByEmail;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetIDByEmailHandler implements RequestHandler<GetIDByEmailRequest, GetIDByEmailResponse> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetIDByEmailResponse handle(GetIDByEmailRequest request) {
        String email = request.getEmail();
        Long userId = userRepositoryPort.getUserIdByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        return new GetIDByEmailResponse(userId);
    }

    @Override
    public Class<GetIDByEmailRequest> getRequestType() {
        return GetIDByEmailRequest.class;
    }
}
