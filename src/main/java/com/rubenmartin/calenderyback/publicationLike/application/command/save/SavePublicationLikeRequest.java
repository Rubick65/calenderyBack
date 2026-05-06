package com.rubenmartin.calenderyback.publicationLike.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePublicationLikeRequest implements Request<Void> {
    private Long idPublicacion;
    private String userEmail;
}
