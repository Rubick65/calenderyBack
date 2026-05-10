package com.rubenmartin.calenderyback.message.infrastructure.database;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import com.rubenmartin.calenderyback.message.infrastructure.database.mapper.MessageEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryPort {

    private final MessageJPARepository messageJPARepository;

    private final MessageEntityMapper messageEntityMapper;

    @Override
    public Optional<Message> saveMessage(Message message) {
        MessageEntity messageEntity = messageEntityMapper.mapToMessageEntity(message);

        return Optional.ofNullable(messageEntityMapper
                .mapToMessage(messageJPARepository
                        .save(messageEntity)
                )
        );

    }

    @Override
    public Optional<Message> getMessageById(Long userId) {
        return messageJPARepository.findById(userId)
                .map(messageEntityMapper::mapToMessage);
    }

    @Override
    public String getLastChatMessage(Long idUsuario, Long id_receptor) {
        return messageJPARepository.getLastChatMessage(idUsuario, id_receptor);
    }

    @Override
    public Page<Message> getMessages(Long idChat, Pageable pageable) {
        return messageJPARepository
                .getMessages(idChat, pageable)
                .map(messageEntityMapper::mapToMessage);
    }
}
