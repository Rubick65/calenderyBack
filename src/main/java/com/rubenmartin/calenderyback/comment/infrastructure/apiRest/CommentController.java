package com.rubenmartin.calenderyback.comment.infrastructure.apiRest;

import com.rubenmartin.calenderyback.comment.application.command.save.SaveCommentRequest;
import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto.CommentDto;
import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.mapper.CommentMapper;
import com.rubenmartin.calenderyback.common.mediator.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController implements CommentRestApi {
    private final Mediator mediator;
    private final CommentMapper commentMapper;

    @Override
    @PostMapping("/app/postComment")
    public ResponseEntity<CommentDto> postComment(@RequestParam("idPublicacion") Long publicationId, @RequestBody CommentDto commentDto, Authentication auth) {
        SaveCommentRequest saveCommentRequest = new SaveCommentRequest(publicationId, commentMapper.mapToComment(commentDto), auth.getName());
        mediator.dispatch(saveCommentRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/app/getComments")
    public ResponseEntity<Page<CommentDto>> getAllComments(@RequestParam("idPublicacion") Long publicationId, Pageable pageable) {
        return null;
    }
}
