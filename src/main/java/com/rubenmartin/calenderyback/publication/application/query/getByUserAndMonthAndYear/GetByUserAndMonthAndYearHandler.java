package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetByUserAndMonthAndYearHandler implements RequestHandler<GetByUserAndMonthAndYearRequest, GetByUserAndMonthAndYearResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final GetSignedUrl getSignedUrl;
    private final String BUCKET_NAME = "fotos_publicaciones";

    @Override
    public GetByUserAndMonthAndYearResponse handle(GetByUserAndMonthAndYearRequest request) {
        Page<Publication> publicationPage = publicationRepositoryPort.findUserPublications(request.getIdUsuario(), request.getMonth(), request.getYear(), request.getPageable());
        List<Publication> publicationsList = publicationPage.getContent().stream().toList();
        List<String> fileNameList = publicationsList.stream().map(Publication::getPublicationFileName).toList();

        Map<String, String> fileSignedUrls = getSignedUrl.createStorageSignedUrls(BUCKET_NAME, fileNameList);

        publicationsList.forEach(publication -> {
            String signedUrl = fileSignedUrls.get(publication.getPublicationFileName());

            if (signedUrl != null)
                publication.setPublicationFileName(signedUrl);

        });

        System.out.println(publicationPage);

        return new GetByUserAndMonthAndYearResponse(publicationPage);
    }

    @Override
    public Class<GetByUserAndMonthAndYearRequest> getRequestType() {
        return GetByUserAndMonthAndYearRequest.class;
    }
}
