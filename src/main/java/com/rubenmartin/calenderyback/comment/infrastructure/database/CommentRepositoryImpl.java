package com.rubenmartin.calenderyback.comment.infrastructure.database;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.domain.port.CommentRepositoryPort;
import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import com.rubenmartin.calenderyback.comment.infrastructure.database.mapper.CommentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryPort {
    private final CommentJPARepository commentJPARepository;
    private final CommentEntityMapper commentEntityMapper;

    @Override
    public Comment saveComment(Comment comment) {
        CommentEntity savedComment = commentJPARepository.save(commentEntityMapper.mapToCommentEntity(comment));

        return commentEntityMapper.mapToComment(savedComment);
    }

    @Override
    public Page<Comment> getComments(Long publicationId, Pageable pageable) {
        Page<CommentEntity> commentPage = commentJPARepository.findByPublication_Id(publicationId, pageable);

        return commentPage.map(commentEntityMapper::mapToComment);

    }
}
