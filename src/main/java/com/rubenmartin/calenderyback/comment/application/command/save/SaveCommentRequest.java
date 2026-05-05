package com.rubenmartin.calenderyback.comment.application.command.save;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveCommentRequest implements Request<Void> {
    private Long idPublicacion;
    private Comment comment;
    private String userEmail;
}
