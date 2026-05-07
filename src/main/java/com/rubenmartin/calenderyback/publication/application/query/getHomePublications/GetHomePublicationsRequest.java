package com.rubenmartin.calenderyback.publication.application.query.getHomePublications;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetHomePublicationsRequest implements Request<GetHomePublicationsResponse> {
    private String userEmail;
    private Pageable pageable;
}
