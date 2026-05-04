package com.rubenmartin.calenderyback.publication.domain.entity;

import com.rubenmartin.calenderyback.comment.domain.entity.Comment;
import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import com.rubenmartin.calenderyback.publicationDate.domain.entity.PublicationDate;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    private Long id;
    private User user;
    private String publicationFileName;
    private String message;
    private int commentaryAmount;
    private int likesAmount;
    private PublicationDate publicationDate;
    private List<Comment> comment;

}
