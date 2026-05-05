package com.rubenmartin.calenderyback.comment.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.publication.infrastructure.database.mapper.PublicationEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {PublicationEntityMapper.class, FollowerEntityMapper.class})
public interface CommentEntityMapper {

    CommentEntity mapToCommentEntity(Comment comment);


    Comment mapToComment(CommentEntity commentEntity);
}
