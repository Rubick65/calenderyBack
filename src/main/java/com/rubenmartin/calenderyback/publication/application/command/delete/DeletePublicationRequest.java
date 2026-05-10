package com.rubenmartin.calenderyback.publication.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePublicationRequest implements Request<Void> {
    private String userEmail;
    private Long publicationId;
}
