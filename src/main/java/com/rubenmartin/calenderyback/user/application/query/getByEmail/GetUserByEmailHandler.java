package com.rubenmartin.calenderyback.user.application.query.getByEmail;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserAlreadyExistException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUserByEmailHandler implements RequestHandler<GetUserByEmailRequest, GetUserByEmailResponse> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetUserByEmailResponse handle(GetUserByEmailRequest request) {
        User user = userRepositoryPort.findUserByEmail(request.getEmail()).orElseThrow(() ->
                new UserAlreadyExistException(request.getEmail()));

        return new GetUserByEmailResponse(user);
    }

    @Override
    public Class<GetUserByEmailRequest> getRequestType() {
        return GetUserByEmailRequest.class;
    }
}
