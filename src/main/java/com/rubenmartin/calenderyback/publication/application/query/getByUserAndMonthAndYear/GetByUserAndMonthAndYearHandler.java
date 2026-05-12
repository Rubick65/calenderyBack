package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetByUserAndMonthAndYearHandler implements RequestHandler<GetByUserAndMonthAndYearRequest, GetByUserAndMonthAndYearResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final PublicationLikeRepositoryPort publicationLikeRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final GetSignedUrl getSignedUrl;
    private final String BUCKET_NAME = "fotos_publicaciones";

    @Override
    public GetByUserAndMonthAndYearResponse handle(GetByUserAndMonthAndYearRequest request) {
        String loggedUserEmail = request.getUserEmail();
        Long idUsuario = request.getIdUsuario();
        Long loggedUserId = userRepositoryPort.getUserIdByEmail(loggedUserEmail).orElseThrow(() -> new UserNotFoundException(loggedUserEmail));

        Page<Publication> publicationPage = publicationRepositoryPort.findUserPublications(idUsuario, request.getMonth(), request.getYear(), request.getPageable());
        List<Publication> publicationsList = publicationPage.getContent().stream().toList();


        publicationsList.forEach(publication -> {
            String signedUrl = getSignedUrl.createPublicUrl(BUCKET_NAME, publication.getPublicationFileName());

            boolean userLikePublication = publicationLikeRepositoryPort.userLiked(loggedUserId, publication.getId());
            publication.setLike(userLikePublication);

            if (signedUrl != null)
                publication.setPublicationFileName(signedUrl);
        });

        return new GetByUserAndMonthAndYearResponse(publicationPage);
    }

    @Override
    public Class<GetByUserAndMonthAndYearRequest> getRequestType() {
        return GetByUserAndMonthAndYearRequest.class;
    }
}
