package com.rubenmartin.calenderyback.user.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveUserHandler implements RequestHandler<SaveUserRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(SaveUserRequest request) {
        userRepositoryPort.upsertUser(request.getUser());
        return null;
    }

    @Override
    public Class<SaveUserRequest> getRequestType() {
        return SaveUserRequest.class;
    }
}
