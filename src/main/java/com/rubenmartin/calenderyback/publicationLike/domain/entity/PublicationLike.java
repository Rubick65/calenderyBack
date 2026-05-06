package com.rubenmartin.calenderyback.publicationLike.domain.entity;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationLike {
    private Long idPublicationLike;
    private User user;
    private Publication publication;
}
