package com.rubenmartin.calenderyback.comment.infrastructure.database.entity;

import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private PublicationEntity publication;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserEntity user;

    @Column(name = "mensaje")
    private String email;

    @Column(name = "fecha_comentario")
    private LocalDateTime commentDate;

}
