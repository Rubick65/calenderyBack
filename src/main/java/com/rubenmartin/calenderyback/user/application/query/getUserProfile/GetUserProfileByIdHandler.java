package com.rubenmartin.calenderyback.user.application.query.getUserProfile;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUserProfileByIdHandler implements RequestHandler<GetUserProfileByIdRequest, GetUserProfileByIdResponse> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetUserProfileByIdResponse handle(GetUserProfileByIdRequest request) {
        Long idUser = request.getUserId();
        User user = userRepositoryPort.findUserById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));

        return GetUserProfileByIdResponse.builder()
                .fotoPerfil(user.getFotoPerfil())
                .descripcion(user.getDescripcion())
                .nombre(user.getNombre())
                .cantidadSeguidos(user.getCantidadSeguidos())
                .cantidadSeguidores(user.getCantidadSeguidores())
                .build();
    }

    @Override
    public Class<GetUserProfileByIdRequest> getRequestType() {
        return GetUserProfileByIdRequest.class;
    }
}
