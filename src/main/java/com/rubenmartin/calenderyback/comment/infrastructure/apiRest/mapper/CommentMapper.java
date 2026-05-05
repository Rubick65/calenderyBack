package com.rubenmartin.calenderyback.comment.infrastructure.apiRest.mapper;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.infrastructure.apiRest.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CommentMapper {

    @Mapping(target = "idUsuario", source = "user.idUsuario")
    @Mapping(target = "nombreUsuario", source = "user.nombre")
    @Mapping(target = "fotoUsuario", source = "user.fotoPerfil")
    @Mapping(target = "idPublicacion", source = "publication.id")
    CommentDto mapToCommentDto(Comment comment);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "publication", ignore = true)
    Comment mapToComment(CommentDto commentDto);
}
