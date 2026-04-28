package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetByUserAndMonthAndYearHandler implements RequestHandler<GetByUserAndMonthAndYearRequest, GetByUserAndMonthAndYearResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;

    @Override
    public GetByUserAndMonthAndYearResponse handle(GetByUserAndMonthAndYearRequest request) {
        Page<Publication> publicationPage = publicationRepositoryPort.findUserPublications(request.idUsuario, request.month, request.year, request.pageable);
        List<Publication> publicationsList = publicationPage.getContent().stream().toList();
        // publicationsList.stream().map(publication -> )

        return null;
    }

    @Override
    public Class<GetByUserAndMonthAndYearRequest> getRequestType() {
        return GetByUserAndMonthAndYearRequest.class;
    }
}
