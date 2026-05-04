package com.rubenmartin.calenderyback.comment.domain.entity;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long commentId;
    private Publication publication;
    private User user;
    private String message;
    private LocalDateTime commentDate;

}
