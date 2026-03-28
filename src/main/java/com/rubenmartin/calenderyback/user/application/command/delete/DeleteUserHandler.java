package com.rubenmartin.calenderyback.user.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteUserHandler implements RequestHandler<DeleteUserRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(DeleteUserRequest request) {
        userRepositoryPort.deleteUser(request.getId());
        return null;
    }

    @Override
    public Class<DeleteUserRequest> getRequestType() {
        return DeleteUserRequest.class;
    }
}
