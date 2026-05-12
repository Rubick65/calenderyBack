package com.rubenmartin.calenderyback.chat.infrastructure.database;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.chat.infrastructure.database.entity.ChatEntity;
import com.rubenmartin.calenderyback.chat.infrastructure.database.mapper.ChatEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepositoryPort {
    private final ChatJPARepository chatJPARepository;
    private final ChatEntityMapper chatEntityMapper;

    @Override
    public Chat saveChat(Chat chat) {
        ChatEntity chatEntity = chatEntityMapper.mapToChatEntity(chat);
        ChatEntity savedChat = chatJPARepository.save(chatEntity);

        return chatEntityMapper.mapToChat(savedChat);
    }

    @Override
    public Optional<Chat> getChatById(Long id) {
        return chatJPARepository.findById(id)
                .map(chatEntityMapper::mapToChat);
    }

    @Override
    public Optional<List<Chat>> getUserChats(Long userId) {
        return Optional.of(chatJPARepository.findByUserId(userId).stream()
                .map(chatEntityMapper::mapToChat).toList());
    }

    @Override
    public Boolean checkIfChatExists(Long userId, Long userToCheckId) {
        return chatJPARepository.checkIfChatExists(userId, userToCheckId);
    }

    @Override
    public Long getChatId(Long user1Id, Long user2Id) {
        return chatJPARepository.getChatId(user1Id, user2Id);
    }
}
