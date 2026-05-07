package com.rubenmartin.calenderyback.publication.application.query.getPublication;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPublicationByIDHandler implements RequestHandler<GetPublicationByIDRequest, GetPublicationByIDResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final PublicationLikeRepositoryPort publicationLikeRepositoryPort;

    @Override
    public GetPublicationByIDResponse handle(GetPublicationByIDRequest request) {
        Long idPost = request.getIdPost();

        Publication publication = publicationRepositoryPort.getPublicationByID(idPost)
                .orElseThrow(() -> new PublicationNotFoundException(idPost));

        boolean userLiked = publicationLikeRepositoryPort.userLiked(publication.getUser().getIdUsuario(), publication.getId());
        publication.setLike(userLiked);

        return new GetPublicationByIDResponse(publication);
    }

    @Override
    public Class<GetPublicationByIDRequest> getRequestType() {
        return GetPublicationByIDRequest.class;
    }
}
