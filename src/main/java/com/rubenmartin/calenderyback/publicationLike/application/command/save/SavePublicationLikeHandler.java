package com.rubenmartin.calenderyback.publicationLike.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publicationLike.domain.entity.PublicationLike;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavePublicationLikeHandler implements RequestHandler<SavePublicationLikeRequest, Void> {

    private final PublicationLikeRepositoryPort publicationLikeRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final PublicationRepositoryPort publicationRepositoryPort;

    @Override
    public Void handle(SavePublicationLikeRequest request) {
        String userEmail = request.getUserEmail();
        Long idPublicacion = request.getIdPublicacion();

        Publication likedPublication = publicationRepositoryPort.getPublicationByID(idPublicacion)
                .orElseThrow(() -> new PublicationNotFoundException(idPublicacion));

        User likeUser = userRepositoryPort.findUserByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        PublicationLike newPublicationLike = PublicationLike.builder()
                .publication(likedPublication)
                .user(likeUser)
                .build();

        publicationLikeRepositoryPort.saveLike(newPublicationLike);
        
        return null;
    }

    @Override
    public Class<SavePublicationLikeRequest> getRequestType() {
        return SavePublicationLikeRequest.class;
    }
}
