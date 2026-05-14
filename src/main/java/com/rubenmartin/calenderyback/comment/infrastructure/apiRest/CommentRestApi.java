package com.rubenmartin.calenderyback.comment.infrastructure.apiRest;

import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto.CommentDto;
import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto.PostCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CommentRestApi {

    ResponseEntity<Long> postComment(PostCommentDto postCommentDto, Authentication auth);

    ResponseEntity<Page<CommentDto>> getAllComments(Long publicationId, Pageable pageable);

    ResponseEntity<Void> deleteComment(Long commentId, Authentication auth);
}
