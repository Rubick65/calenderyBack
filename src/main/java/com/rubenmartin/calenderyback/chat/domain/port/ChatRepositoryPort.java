package com.rubenmartin.calenderyback.chat.domain.port;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepositoryPort {
    void saveChat(Chat chat);

    Optional<Chat> getChatById(Long id);

    Optional<List<Chat>> getUserChats(Long userId);

}
