package com.rubenmartin.calenderyback.comment.application.command.save;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.domain.port.CommentRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.exception.PublicationNotFoundException;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SaveCommentHandler implements RequestHandler<SaveCommentRequest, SaveCommentResponse> {
    private final CommentRepositoryPort commentRepositoryPort;
    private final PublicationRepositoryPort publicationRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public SaveCommentResponse handle(SaveCommentRequest request) {
        Long idPublicacion = request.getIdPublicacion();
        String email = request.getUserEmail();
        String comentario = request.getComentario();

        User commentUser = userRepositoryPort
                .findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        Publication commentPublication = publicationRepositoryPort
                .getPublicationByID(idPublicacion)
                .orElseThrow(() -> new PublicationNotFoundException(idPublicacion));

        Comment comment = Comment.builder()
                .user(commentUser)
                .publication(commentPublication)
                .comentario(comentario)
                .localDateTimeData(LocalDateTime.now())
                .build();

        Comment savedComment = commentRepositoryPort.saveComment(comment);

        return new SaveCommentResponse(savedComment.getIdComentario());
    }

    @Override
    public Class<SaveCommentRequest> getRequestType() {
        return SaveCommentRequest.class;
    }
}
