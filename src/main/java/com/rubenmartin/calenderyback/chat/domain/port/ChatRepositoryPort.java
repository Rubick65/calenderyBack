package com.rubenmartin.calenderyback.chat.domain.port;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepositoryPort {
    Chat saveChat(Chat chat);

    Optional<Chat> getChatById(Long id);

    Optional<List<Chat>> getUserChats(Long userId);

    Boolean checkIfChatExists(Long userId, Long userToCheckId);

    Long getChatId(Long user1Id, Long user2Id);

}
