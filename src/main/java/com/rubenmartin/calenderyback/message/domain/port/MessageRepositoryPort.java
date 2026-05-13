package com.rubenmartin.calenderyback.message.domain.port;


import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.model.LastMessageDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageRepositoryPort {

    Optional<Message> saveMessage(Message message);

    Optional<Message> getMessageById(Long userId);

    LastMessageDataModel getLastChatMessage(Long idUsuario, Long id_receptor);

    Page<Message> getMessages(Long chatId, Pageable pageable);

    int changeMessageState(Long messageId, EstadoMensaje messageState);

    int changeAllChatMessagesState(Long userId, Long chatId, EstadoMensaje messageState);

    Boolean checkForPendingMessages(Long userId);
}
