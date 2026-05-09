package com.rubenmartin.calenderyback.message.infrastructure.database;

import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageJPARepository extends JpaRepository<MessageEntity, Long> {

    @Query(value = "SELECT m.contenido FROM mensaje_chat m " +
            "WHERE (m.id_usuario = :idUsuario" +
            " AND m.id_receptor = :id_receptor)" +
            " OR (m.id_usuario = :id_receptor" +
            " AND m.id_receptor = :idUsuario)" +
            " ORDER BY m.fecha_mensaje DESC" +
            " LIMIT 1", nativeQuery = true)
    String getLastChatMessage(Long idUsuario, Long id_receptor);

}
