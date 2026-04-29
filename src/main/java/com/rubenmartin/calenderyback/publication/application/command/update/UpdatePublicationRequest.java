package com.rubenmartin.calenderyback.publication.application.command.update;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdatePublicationRequest implements Request<UpdatePublicationResponse> {
    private Publication publicationToUpdate;

    private String message;
    private Long idUsuario;
    private LocalDate calendarDate;
}
