package com.rubenmartin.calenderyback.user.application.command.register;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.domain.exception.RolNotFoundException;
import com.rubenmartin.calenderyback.rol.domain.port.RolRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserAlreadyExistException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private static final String USER_ROL = "ROLE_USER";
    private final UserRepositoryPort userRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {

        if (userExist(request))
            throw new UserAlreadyExistException(request.getEmail());
        // Create an encoder with strength 12
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String result = encoder.encode(request.getKeypass());

        User newUser = User.builder()
                .nombre(request.getNombre()).
                email(request.getEmail()).
                keypass(result).
                enable(false).
                build();
        Rol user_rol = rolRepositoryPort.findByName(USER_ROL).orElseThrow(() -> new RolNotFoundException(USER_ROL));

        newUser.setRoles(List.of(user_rol));

        User registeredUser = userRepositoryPort.upsertUser(newUser);

        return new RegisterUserResponse(registeredUser);
    }

    private boolean userExist(RegisterUserRequest request) {
        return userRepositoryPort.findUserByEmail(request.getEmail()).isPresent();

    }

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }
}
