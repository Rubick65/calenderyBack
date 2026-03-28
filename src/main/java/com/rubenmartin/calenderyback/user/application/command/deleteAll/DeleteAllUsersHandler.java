package com.rubenmartin.calenderyback.user.application.command.deleteAll;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteAllUsersHandler implements RequestHandler<DeleteAllUsersRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(DeleteAllUsersRequest request) {
        userRepositoryPort.deleteAllUsers();
        return null;
    }

    @Override
    public Class<DeleteAllUsersRequest> getRequestType() {
        return DeleteAllUsersRequest.class;
    }
}
