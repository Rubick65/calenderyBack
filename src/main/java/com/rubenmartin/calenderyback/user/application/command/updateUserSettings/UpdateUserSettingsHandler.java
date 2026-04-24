package com.rubenmartin.calenderyback.user.application.command.updateUserSettings;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateUserSettingsHandler implements RequestHandler<UpdateUserSettingsRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(UpdateUserSettingsRequest request) {
        Long userId = request.getIdUsuario();

        User user = userRepositoryPort.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        user.setNombre(request.getNombre());
        user.setDescripcion(request.getDescripcion());

        userRepositoryPort.upsertUser(user);

        return null;
    }

    @Override
    public Class<UpdateUserSettingsRequest> getRequestType() {
        return UpdateUserSettingsRequest.class;
    }
}
