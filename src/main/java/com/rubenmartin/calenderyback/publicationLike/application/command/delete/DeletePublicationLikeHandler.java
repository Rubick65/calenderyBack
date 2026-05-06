package com.rubenmartin.calenderyback.publicationLike.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePublicationLikeHandler implements RequestHandler<DeletePublicationLikeRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;
    private final PublicationLikeRepositoryPort publicationLikeRepositoryPort;

    @Override
    public Void handle(DeletePublicationLikeRequest request) {
        String userEmail = request.getUserEmail();

        Long publicationId = request.getPublicationId();
        Long userId = userRepositoryPort.getUserIdByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
        
        publicationLikeRepositoryPort.deletePublicationLike(publicationId, userId);

        return null;
    }

    @Override
    public Class<DeletePublicationLikeRequest> getRequestType() {
        return DeletePublicationLikeRequest.class;
    }
}
