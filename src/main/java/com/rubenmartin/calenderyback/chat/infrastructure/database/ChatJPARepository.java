package com.rubenmartin.calenderyback.chat.infrastructure.database;

import com.rubenmartin.calenderyback.chat.infrastructure.database.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatJPARepository extends JpaRepository<ChatEntity, Long> {

    @Query("SELECT c FROM ChatEntity c WHERE c.user1.idUsuario = :userId OR c.user2.idUsuario = :userId")
    List<ChatEntity> findByUserId(@Param("userId") Long userId);
}
