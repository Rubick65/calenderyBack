package com.rubenmartin.calenderyback.publication.application.query.getPublication;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPublicationByIDResponse {
    private Publication publication;
}
