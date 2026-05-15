package com.rubenmartin.calenderyback.message.infrastructure.database;

import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import com.rubenmartin.calenderyback.message.infrastructure.projection.LastMessageDataProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageJPARepository extends JpaRepository<MessageEntity, Long> {

    @Query(value = "SELECT " +
            "  c.id_chat AS chatId, " +
            "  CASE " +
            "    WHEN m.id_usuario = :idUsuario THEN m.mensaje_propio " +
            "    ELSE m.contenido " +
            "  END AS contenido," +
            "  CASE WHEN m.estado <> 'LEIDO' THEN true ELSE false END AS noRead " +
            "FROM chat c " +
            "LEFT JOIN mensaje_chat m ON c.id_chat = m.id_chat " +
            "WHERE (c.id_usuario1 = :idUsuario AND c.id_usuario2 = :id_receptor) " +
            "   OR (c.id_usuario1 = :id_receptor AND c.id_usuario2 = :idUsuario) " +
            "ORDER BY m.fecha_mensaje DESC " +
            "LIMIT 1", nativeQuery = true)
    LastMessageDataProjection getLastChatMessage(@Param("idUsuario") Long idUsuario, @Param("id_receptor") Long id_receptor);

    @Query("SELECT m FROM MessageEntity m " +
            "WHERE m.chatId.id = :chatId")
    Page<MessageEntity> getMessages(@Param("chatId") Long chatId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE MessageEntity m SET m.messageState = :state WHERE m.id = :messageId")
    int changeMessageState(@Param("messageId") Long messageId, @Param("state") EstadoMensaje messageState);

    @Modifying
    @Transactional
    @Query("UPDATE MessageEntity m " +
            "SET m.messageState = :state " +
            "WHERE (m.chatId.id = :idChat AND m.toUser.idUsuario = :idUsuario)")
    int changeAllChatMessagesState(@Param("idChat") Long idChat, @Param("idUsuario") Long idUsuario, @Param("state") EstadoMensaje messageState);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true else false END " +
            "FROM MessageEntity m " +
            "WHERE ((m.fromUser.idUsuario = :userId OR m.toUser.idUsuario = :userId) " +
            "AND  m.messageState = :state)")
    boolean checkIfForPendingMessages(@Param("userId") Long userId, @Param("state") EstadoMensaje messageState);

}
