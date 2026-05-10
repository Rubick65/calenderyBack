package com.rubenmartin.calenderyback.message.infrastructure.database;

import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageJPARepository extends JpaRepository<MessageEntity, Long> {

    @Query(value = "SELECT m.contenido FROM mensaje_chat m " +
            "WHERE (m.id_usuario = :idUsuario" +
            " AND m.id_receptor = :id_receptor)" +
            " OR (m.id_usuario = :id_receptor" +
            " AND m.id_receptor = :idUsuario)" +
            " ORDER BY m.fecha_mensaje DESC" +
            " LIMIT 1", nativeQuery = true)
    String getLastChatMessage(Long idUsuario, Long id_receptor);

    @Query("SELECT m FROM MessageEntity m " +
            "WHERE m.chatId.id = :chatId")
    Page<MessageEntity> getMessages(@Param("chatId") Long chatId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE MessageEntity m SET m.messageState = :state WHERE m.id = :messageId")
    int changeMessageState(@Param("messageId") Long messageId, @Param("state") EstadoMensaje messageState);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true else false END " +
            "FROM MessageEntity m " +
            "WHERE ((m.fromUser.idUsuario = :userId OR m.toUser.idUsuario = :userId) " +
            "AND  m.messageState = :state)")
    boolean checkIfForPendingMessages(@Param("userId") Long userId, @Param("state") EstadoMensaje messageState);

}
