package com.rubenmartin.calenderyback.user.application.command.checkPublicKey;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publicationLike.domain.exception.PublicationLikeNotFoundedException;
import com.rubenmartin.calenderyback.user.domain.exception.PublicKeyNotCoincidentException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CheckUserPublicKeyHandler implements RequestHandler<CheckUserPublicKeyRequest, Void> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(CheckUserPublicKeyRequest request) {
        String publicKey = request.getPublicKey();
        Long userId = request.getUserId();

        String userPublicKey = userRepositoryPort
                .getUserPublicKey(userId)
                .orElseThrow(() -> new PublicationLikeNotFoundedException(userId));

        if (!publicKey.equals(userPublicKey))
            throw new PublicKeyNotCoincidentException(userId);
        return null;
    }

    @Override
    public Class<CheckUserPublicKeyRequest> getRequestType() {
        return CheckUserPublicKeyRequest.class;
    }
}
