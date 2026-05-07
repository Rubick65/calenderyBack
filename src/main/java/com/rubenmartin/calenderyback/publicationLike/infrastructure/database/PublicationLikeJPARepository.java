package com.rubenmartin.calenderyback.publicationLike.infrastructure.database;

import com.rubenmartin.calenderyback.publicationLike.infrastructure.database.entity.PublicationLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicationLikeJPARepository extends JpaRepository<PublicationLikeEntity, Long> {

    @Query("SELECT COUNT(p) FROM PublicationLikeEntity p WHERE p.publication.id = :publicationId")
    int countPublicationLikes(@Param("publicationId") Long publicationId);

    @Modifying
    @Query("DELETE FROM PublicationLikeEntity p WHERE p.publication.id = :publicationId AND p.user.idUsuario = :userId")
    int deletePublicationLike(@Param("publicationId") Long publicationId, @Param("userId") Long userId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PublicationLikeEntity p " +
            "WHERE p.user.idUsuario = :userId AND p.publication.id = :publicationId")
    boolean userLiked(@Param("userId") Long userId, @Param("publicationId") Long publicationId);
}
