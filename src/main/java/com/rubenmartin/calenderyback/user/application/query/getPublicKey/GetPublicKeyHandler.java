package com.rubenmartin.calenderyback.user.application.query.getPublicKey;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPublicKeyHandler implements RequestHandler<GetPublicKeyRequest, GetPublicKeyResponse> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetPublicKeyResponse handle(GetPublicKeyRequest request) {
        Long idUsuario = request.getIdUsuario();
        return null;
    }

    @Override
    public Class<GetPublicKeyRequest> getRequestType() {
        return GetPublicKeyRequest.class;
    }
}
