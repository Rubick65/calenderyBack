package com.rubenmartin.calenderyback.comment.infrastructure.database;

import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJPARepository extends JpaRepository<CommentEntity, Long> {

    Page<CommentEntity> findByPublication_Id(Long publicationId, Pageable pageable);
}
