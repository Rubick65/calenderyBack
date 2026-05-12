package com.rubenmartin.calenderyback.chat.infrastructure.database;

import com.rubenmartin.calenderyback.chat.infrastructure.database.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatJPARepository extends JpaRepository<ChatEntity, Long> {

    @Query("SELECT c FROM ChatEntity c WHERE c.user1.idUsuario = :userId OR c.user2.idUsuario = :userId")
    List<ChatEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM ChatEntity c " +
            "WHERE (c.user1.idUsuario = :userId AND c.user2.idUsuario = :userToCheckId)" +
            "OR (c.user2.idUsuario = :userId AND c.user1.idUsuario = :userToCheckId) ")
    Boolean checkIfChatExists(@Param("userId") Long userId, @Param("userToCheckId") Long userToCheckId);

    @Query("SELECT c.id FROM ChatEntity c " +
            "WHERE ((c.user1.idUsuario = :user1Id AND c.user2.idUsuario = :user2Id) " +
            "OR (c.user1.idUsuario = :user2Id AND c.user2.idUsuario =: user1Id)) ")
    Long getChatId(Long user1Id, Long user2Id);

}
