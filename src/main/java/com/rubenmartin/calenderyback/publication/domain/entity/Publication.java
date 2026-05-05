package com.rubenmartin.calenderyback.publication.domain.entity;

import com.rubenmartin.calenderyback.publicationDate.domain.entity.PublicationDate;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
