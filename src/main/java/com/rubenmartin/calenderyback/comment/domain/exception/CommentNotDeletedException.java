package com.rubenmartin.calenderyback.comment.domain.exception;

public class CommentNotDeletedException extends RuntimeException {
    public CommentNotDeletedException(Long commentId) {
        super("Comment with " + commentId + "id couldn't be deleted.");
    }
}
