package com.rubenmartin.calenderyback.comment.application.query.getAllPublicationComments;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetCommentsPublicationByPublicationIDResponse {
    private Page<Comment> commentsPage;
}
