package com.rubenmartin.calenderyback.publication.application.query.getPublication;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPublicationByIDRequest implements Request<GetPublicationByIDResponse> {
    private Long idPost;
}
