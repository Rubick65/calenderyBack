package com.rubenmartin.calenderyback.user.application.command.create;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.domain.exception.RolNotFoundException;
import com.rubenmartin.calenderyback.rol.domain.port.RolRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserAlreadyExistException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements RequestHandler<CreateUserRequest, Void> {

    private static final String USER_ROL = "ROLE_USER";
    private final UserRepositoryPort userRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public Void handle(CreateUserRequest request) {

        // TODO mejor cambiar esto por un caso de prueba que devuelva un booleano, ahorra memoría
        if (userExist(request))
            throw new UserAlreadyExistException(request.getEmail());

        User newUser = User.builder()
                .nombre(request.getNombre()).
                email(request.getEmail()).
                keypass(request.getKeypass()).
                enable(false).
                clavePublica(request.getClavePublica()).build();

        Rol user_rol = rolRepositoryPort.findByName(USER_ROL).orElseThrow(() -> new RolNotFoundException(USER_ROL));

        newUser.setRoles(List.of(user_rol));

        userRepositoryPort.upsertUser(newUser);

        return null;
    }

    private boolean userExist(CreateUserRequest request) {
        return userRepositoryPort.findUserByEmail(request.getEmail()).isPresent();

    }

    @Override
    public Class<CreateUserRequest> getRequestType() {
        return CreateUserRequest.class;
    }
}
