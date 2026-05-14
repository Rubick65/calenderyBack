package com.rubenmartin.calenderyback.comment.infrastructure.database;

import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJPARepository extends JpaRepository<CommentEntity, Long> {

    Page<CommentEntity> findByPublication_Id(Long publicationId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM CommentEntity c WHERE c.idComentario = :commentId AND c.user.idUsuario = :userId")
    int deleteComment(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
