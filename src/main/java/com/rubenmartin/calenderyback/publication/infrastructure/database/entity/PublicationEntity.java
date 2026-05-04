package com.rubenmartin.calenderyback.publication.infrastructure.database.entity;

import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import com.rubenmartin.calenderyback.publicationDate.infrastructure.database.entity.PublicationDateEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "publicacion")
public class PublicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @Column(name = "foto_publicacion")
    private String publicationFileName;

    @Column(name = "mensaje")
    private String message;

    @Column(name = "cantidad_comentarios")
    private int commentaryAmount;

    @Column(name = "cantidad_likes")
    private int likesAmount;

    @OneToOne(mappedBy = "publication", cascade = CascadeType.ALL)
    private PublicationDateEntity publicationDate;

    @OneToMany(mappedBy = "publication")
    private List<CommentEntity> comments;

    public void setPublicationDate(PublicationDateEntity publicationDate) {
        this.publicationDate = publicationDate;
        if (publicationDate != null) {
            publicationDate.setPublication(this);
        }
    }

}
