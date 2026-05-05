package com.rubenmartin.calenderyback.comment.infrastructure.apiRest;

import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface CommentRestApi {

    ResponseEntity<CommentDto> postComment(Long publicationId, CommentDto commentDto, Authentication auth);

    ResponseEntity<Page<CommentDto>> getAllComments(Long publicationId, Pageable pageable);
}
