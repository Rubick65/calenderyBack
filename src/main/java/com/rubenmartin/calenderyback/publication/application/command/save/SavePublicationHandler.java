package com.rubenmartin.calenderyback.publication.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavePublicationHandler implements RequestHandler<SavePublicationRequest, SavePublicationResponse> {
    private final PublicationRepositoryPort publicationRepositoryPort;

    @Override
    public SavePublicationResponse handle(SavePublicationRequest request) {
        Publication savedPublication = publicationRepositoryPort.savePublication(request.getPublication());

        return new SavePublicationResponse(savedPublication);
    }

    @Override
    public Class<SavePublicationRequest> getRequestType() {
        return SavePublicationRequest.class;
    }
}
