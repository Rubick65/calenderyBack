package com.rubenmartin.calenderyback.comment.domain.port;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryPort {

    Comment saveComment(Comment comment);

    List<Comment> getComments(Long publicationId);
}
