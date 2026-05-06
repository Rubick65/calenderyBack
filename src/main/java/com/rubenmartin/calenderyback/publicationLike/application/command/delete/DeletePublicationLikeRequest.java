package com.rubenmartin.calenderyback.publicationLike.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePublicationLikeRequest implements Request<Void> {
    private Long publicationId;
    private String userEmail;
}
