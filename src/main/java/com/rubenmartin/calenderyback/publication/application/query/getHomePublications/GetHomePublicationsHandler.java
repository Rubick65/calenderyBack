package com.rubenmartin.calenderyback.publication.application.query.getHomePublications;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetHomePublicationsHandler implements RequestHandler<GetHomePublicationsRequest, GetHomePublicationsResponse> {
    private final UserRepositoryPort userRepositoryPort;
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final PublicationLikeRepositoryPort publicationLikeRepositoryPort;
    private final GetSignedUrl getSignedUrl;
    private final String BUCKET_NAME = "fotos_publicaciones";
    
    @Override
    public GetHomePublicationsResponse handle(GetHomePublicationsRequest request) {
        String loggedUserEmail = request.getUserEmail();
        Long loggedUserId = userRepositoryPort.getUserIdByEmail(loggedUserEmail).orElseThrow(() -> new UserNotFoundException(loggedUserEmail));
        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "publicationDate.uploadDate"));

        int mont = LocalDate.now().getMonthValue();

        Page<Publication> publicationPage = publicationRepositoryPort.getMonthHomePublications(mont, loggedUserId, pageable);
        List<Publication> publicationsList = publicationPage.getContent().stream().toList();
        List<String> fileNameList = publicationsList.stream().map(Publication::getPublicationFileName).toList();

        Map<String, String> fileSignedUrls = getSignedUrl.createStorageSignedUrls(BUCKET_NAME, fileNameList);

        publicationsList.forEach(publication -> {
            String signedUrl = fileSignedUrls.get(publication.getPublicationFileName());

            boolean userLikePublication = publicationLikeRepositoryPort.userLiked(loggedUserId, publication.getId());
            publication.setLike(userLikePublication);

            if (signedUrl != null)
                publication.setPublicationFileName(signedUrl);
        });
        return new GetHomePublicationsResponse(publicationPage);
    }

    @Override
    public Class<GetHomePublicationsRequest> getRequestType() {
        return GetHomePublicationsRequest.class;
    }
}
