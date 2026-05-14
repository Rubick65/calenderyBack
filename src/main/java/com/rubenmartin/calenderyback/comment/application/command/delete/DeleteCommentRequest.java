package com.rubenmartin.calenderyback.comment.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCommentRequest implements Request<Void> {
    private Long commentId;
    private String userEmail;
}
