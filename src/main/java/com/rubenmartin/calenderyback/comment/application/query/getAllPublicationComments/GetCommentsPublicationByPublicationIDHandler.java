package com.rubenmartin.calenderyback.comment.application.query.getAllPublicationComments;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.domain.port.CommentRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCommentsPublicationByPublicationIDHandler implements RequestHandler<GetCommentsPublicationByPublicationIDRequest, GetCommentsPublicationByPublicationIDResponse> {

    private final CommentRepositoryPort commentRepositoryPort;

    @Override
    public GetCommentsPublicationByPublicationIDResponse handle(GetCommentsPublicationByPublicationIDRequest request) {
        Long publicationID = request.getIdPublicacion();
        Pageable pageable = request.getPageable();

        Page<Comment> commentsPage = commentRepositoryPort.getComments(publicationID, pageable);

        return new GetCommentsPublicationByPublicationIDResponse(commentsPage);
    }

    @Override
    public Class<GetCommentsPublicationByPublicationIDRequest> getRequestType() {
        return GetCommentsPublicationByPublicationIDRequest.class;
    }
}
