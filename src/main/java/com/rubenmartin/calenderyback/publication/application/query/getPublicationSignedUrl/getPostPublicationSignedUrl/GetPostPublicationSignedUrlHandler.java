package com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GetPostPublicationSignedUrlHandler implements RequestHandler<GetPostPublicationSignedUrlRequest, GetPostPublicationSignedUrlResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final GetSignedUrl getSignedUrl;
    private final String BUCKET_NAME = "fotos_publicaciones";

    @Override
    public GetPostPublicationSignedUrlResponse handle(GetPostPublicationSignedUrlRequest request) {
        String path = generateFileName();
        User userPublication = userRepositoryPort.findUserByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException(request.getEmail()));
        String url = getSignedUrl.createUploadSignedUrl(BUCKET_NAME, path);

        Publication newPublication = Publication.builder()
                .publicationFileName(path)
                .user(userPublication)
                .build();

        Publication savedPublication = publicationRepositoryPort.savePublication(newPublication);


        return new GetPostPublicationSignedUrlResponse(savedPublication.getId(), url);
    }

    @Override
    public Class<GetPostPublicationSignedUrlRequest> getRequestType() {
        return GetPostPublicationSignedUrlRequest.class;
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
