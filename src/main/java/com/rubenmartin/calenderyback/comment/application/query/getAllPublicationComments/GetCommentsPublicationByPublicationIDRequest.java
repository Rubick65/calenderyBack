package com.rubenmartin.calenderyback.comment.application.query.getAllPublicationComments;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetCommentsPublicationByPublicationIDRequest implements Request<GetCommentsPublicationByPublicationIDResponse> {
    private Long idPublicacion;
    private Pageable pageable;
}
