package com.rubenmartin.calenderyback.comment.infrastructure.database;

import com.rubenmartin.calenderyback.comment.infrastructure.database.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJPARepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPublication_Id(Long publicationId);
}
