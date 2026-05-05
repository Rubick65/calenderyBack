package com.rubenmartin.calenderyback.comment.domain.port;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryPort {

    Comment saveComment(Comment comment);

    Page<Comment> getComments(Long publicationId, Pageable pageable);
}
