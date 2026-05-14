package com.rubenmartin.calenderyback.comment.application.command.delete;

import com.rubenmartin.calenderyback.comment.domain.exception.CommentNotDeletedException;
import com.rubenmartin.calenderyback.comment.domain.port.CommentRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCommentHandler implements RequestHandler<DeleteCommentRequest, Void> {
    private final CommentRepositoryPort commentRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(DeleteCommentRequest request) {
        Long commentId = request.getCommentId();
        String userEmail = request.getUserEmail();

        Long userId = userRepositoryPort
                .getUserIdByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        int response = commentRepositoryPort.deleteComment(commentId, userId);

        if (response == 0)
            throw new CommentNotDeletedException(commentId);


        return null;
    }

    @Override
    public Class<DeleteCommentRequest> getRequestType() {
        return DeleteCommentRequest.class;
    }
}
