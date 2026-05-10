package com.rubenmartin.calenderyback.publication.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationDeleteException;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePublicationHandler implements RequestHandler<DeletePublicationRequest, Void> {

    private final UserRepositoryPort userRepositoryPort;
    private final PublicationRepositoryPort publicationRepositoryPort;

    @Override
    public Void handle(DeletePublicationRequest request) {
        String userEmail = request.getUserEmail();
        Long publicationId = request.getPublicationId();

        Long userId = userRepositoryPort
                .getUserIdByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        Publication publication = publicationRepositoryPort
                .getPublicationByID(publicationId)
                .orElseThrow(() -> new PublicationNotFoundException(publicationId));

        if (publication.getUser().getIdUsuario().intValue() != userId)
            throw new PublicationDeleteException(userId, publicationId);


        return null;
    }

    @Override
    public Class<DeletePublicationRequest> getRequestType() {
        return DeletePublicationRequest.class;
    }
}
