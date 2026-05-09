package com.rubenmartin.calenderyback.message.domain.port;


import com.rubenmartin.calenderyback.message.domain.entity.Message;

import java.util.Optional;

public interface MessageRepositoryPort {

    Optional<Message> saveMessage(Message message);

    Optional<Message> getMessageById(Long userId);

    String getLastChatMessage(Long idUsuario, Long id_receptor);

}
