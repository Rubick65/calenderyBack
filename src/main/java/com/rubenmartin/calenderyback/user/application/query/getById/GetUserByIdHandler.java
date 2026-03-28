package com.rubenmartin.calenderyback.user.application.query.getById;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUserByIdHandler implements RequestHandler<GetUserByIdRequest, GetUserByIdResponse> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetUserByIdResponse handle(GetUserByIdRequest request) {
        User user = userRepositoryPort.findUserById(request.getId()).orElseThrow(() ->
                new UserNotFoundException(request.getId()));
        return new GetUserByIdResponse(user);
    }

    @Override
    public Class<GetUserByIdRequest> getRequestType() {
        return GetUserByIdRequest.class;
    }
}
