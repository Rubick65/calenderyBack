package com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostPublicationSignedUrlRequest implements Request<GetPostPublicationSignedUrlResponse> {
    private String email;
}
