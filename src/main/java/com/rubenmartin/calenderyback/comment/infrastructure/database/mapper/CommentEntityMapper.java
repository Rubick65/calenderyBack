package com.rubenmartin.calenderyback.comment.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CommentEntityMapper {

    @Mapping(target ="publication.comments", ignore = true )
    CommentEntity mapToCommentEntity(Comment comment);

    @Mapping(target = "publication.comment", ignore = true)
    Comment mapToComment(CommentEntity commentEntity);
}
