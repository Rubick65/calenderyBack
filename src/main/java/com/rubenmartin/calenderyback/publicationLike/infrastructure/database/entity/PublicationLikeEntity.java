package com.rubenmartin.calenderyback.publicationLike.infrastructure.database.entity;

import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "like_publication")
public class PublicationLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicationLike;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicationEntity publication;
}
