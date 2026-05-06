package com.rubenmartin.calenderyback.publicationLike.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
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
    private final PublicationRepositoryPort publicationRepositoryPort;

    @Override
    public Void handle(DeletePublicationLikeRequest request) {
        String userEmail = request.getUserEmail();
        Long publicationId = request.getPublicationId();

        Publication removedPublicationLike = publicationRepositoryPort.getPublicationByID(publicationId)
                .orElseThrow(() -> new PublicationNotFoundException(publicationId));
        removedPublicationLike.setLikesAmount(removedPublicationLike.getLikesAmount() - 1);

        Long userId = userRepositoryPort.getUserIdByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        publicationRepositoryPort.savePublication(removedPublicationLike);
        publicationLikeRepositoryPort.deletePublicationLike(publicationId, userId);

        return null;
    }

    @Override
    public Class<DeletePublicationLikeRequest> getRequestType() {
        return DeletePublicationLikeRequest.class;
    }
}
