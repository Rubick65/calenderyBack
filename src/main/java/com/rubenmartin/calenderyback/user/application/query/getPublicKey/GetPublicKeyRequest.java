package com.rubenmartin.calenderyback.user.application.query.getPublicKey;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPublicKeyRequest implements Request<GetPublicKeyResponse> {
    private Long idUsuario;
}
