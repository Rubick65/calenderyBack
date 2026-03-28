package com.rubenmartin.calenderyback.user.application.command.create;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserAlreadyExistException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUserRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(CreateUserRequest request) {

        // TODO mejor cambiar esto por un caso de prueba que devuelva un booleano, ahorra memoría
        userRepositoryPort.findUserByEmail(request.getEmail()).orElseThrow(()
                -> new UserAlreadyExistException(request.getEmail()));


        User newUser = User.builder()
                .idUsuario(request.getIdUsuario())
                .nombre(request.getNombre())
                .email(request.getEmail())
                .keypass(request.getKeypass())
                .clavePublica(request.getClavePublica())
                .build();

        userRepositoryPort.upsertUser(newUser);

        return null;
    }

    @Override
    public Class<CreateUserRequest> getRequestType() {
        return CreateUserRequest.class;
    }
}
