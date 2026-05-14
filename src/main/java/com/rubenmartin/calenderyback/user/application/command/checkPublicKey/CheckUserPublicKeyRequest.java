package com.rubenmartin.calenderyback.user.application.command.checkPublicKey;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckUserPublicKeyRequest implements Request<Void> {
    private Long userId;
    private String publicKey;

}
