package com.rubenmartin.calenderyback.user.application.query.getUserSettings;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GetUserSettingByIdHandler implements RequestHandler<GetUserSettingsByIdRequest, GetUserSettingsByIdResponse> {

    private UserRepositoryPort userRepositoryPort;

    @Override
    public GetUserSettingsByIdResponse handle(GetUserSettingsByIdRequest request) {

        Long userId = request.getId();

        User user = userRepositoryPort.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        return GetUserSettingsByIdResponse.builder()
                .nombre(user.getNombre())
                .descripcion(user.getDescripcion())
                .fotoPerfil(user.getFotoPerfil())
                .build();
    }

    @Override
    public Class<GetUserSettingsByIdRequest> getRequestType() {
        return GetUserSettingsByIdRequest.class;
    }
}
