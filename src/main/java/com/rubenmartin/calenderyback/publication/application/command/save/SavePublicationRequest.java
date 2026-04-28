package com.rubenmartin.calenderyback.publication.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePublicationRequest implements Request<SavePublicationResponse> {
    private Publication publication;
}
