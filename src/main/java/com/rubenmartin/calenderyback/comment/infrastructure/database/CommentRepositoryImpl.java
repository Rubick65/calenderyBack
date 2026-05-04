package com.rubenmartin.calenderyback.comment.infrastructure.database;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.domain.port.CommentRepositoryPort;
import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import com.rubenmartin.calenderyback.comment.infrastructure.database.mapper.CommentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Comment> getComments(Long publicationId) {
        return  commentJPARepository.findByPublication_Id(publicationId)
                .stream()
                .map(commentEntityMapper::mapToComment).toList();
    }
}
