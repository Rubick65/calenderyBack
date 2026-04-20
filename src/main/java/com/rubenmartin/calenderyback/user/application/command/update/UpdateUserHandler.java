package com.rubenmartin.calenderyback.user.application.command.update;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, Void> {
    private final UserRepositoryPort userRepositoryPort;


    @Override
    public Void handle(UpdateUserRequest request) {
        User updatedUser = User.builder()
                .idUsuario(request.getIdUsuario())
                .enable(request.isEnable())
                .fotoPerfil(request.getFotoPerfil())
                .nombre(request.getNombre())
                .email(request.getEmail())
                .keypass(request.getKeypass())
                .clavePublica(request.getClavePublica())
                .roles(request.getRoles())
                .build();

        userRepositoryPort.upsertUser(updatedUser);
        return null;
    }

    @Override
    public Class<UpdateUserRequest> getRequestType() {
        return UpdateUserRequest.class;
    }
}
