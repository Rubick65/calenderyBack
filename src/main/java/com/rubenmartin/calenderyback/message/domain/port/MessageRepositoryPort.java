package com.rubenmartin.calenderyback.message.domain.port;


import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageRepositoryPort {

    Optional<Message> saveMessage(Message message);

    Optional<Message> getMessageById(Long userId);

    String getLastChatMessage(Long idUsuario, Long id_receptor);

    Page<Message> getMessages(Long chatId, Pageable pageable);

    int changeMessageState(Long messageId, EstadoMensaje messageState);

    Boolean checkForPendingMessages(Long userId);
}
