package com.rubenmartin.calenderyback.publication.application.command.update;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publicationDate.domain.entity.PublicationDate;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UpdatePublicationHandler implements RequestHandler<UpdatePublicationRequest, UpdatePublicationResponse> {

    private final PublicationRepositoryPort publicationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UpdatePublicationResponse handle(UpdatePublicationRequest request) {
        String message = request.getMessage();
        Long idUsuario = request.getIdUsuario();

        Publication publicationToUpdate = request.getPublicationToUpdate();

        PublicationDate publicationDate = PublicationDate.builder()
                .publication(publicationToUpdate)
                .uploadDate(LocalDate.now())
                .calendarDate(request.getCalendarDate())
                .build();

        User user = userRepositoryPort.findUserById(idUsuario).orElseThrow(() -> new UserNotFoundException(idUsuario));

        publicationToUpdate.setUser(user);
        publicationToUpdate.setPublicationDate(publicationDate);

        if (!message.isEmpty())
            publicationToUpdate.setMessage(request.getMessage());

        Publication updatedPublication = publicationRepositoryPort.savePublication(publicationToUpdate);

        return new UpdatePublicationResponse(updatedPublication);
    }

    @Override
    public Class<UpdatePublicationRequest> getRequestType() {
        return UpdatePublicationRequest.class;
    }
}
