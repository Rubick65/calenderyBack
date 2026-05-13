package com.rubenmartin.calenderyback.message.infrastructure.apiRest;

import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface MessageRestApi {

    ResponseEntity<Page<MessageResponseDto>> getMessages(Long idChat, Long usuarioActual, Pageable pageable);

    ResponseEntity<Void> changeMessageToSendState(Long messageId);

    ResponseEntity<Void> changeMessageToReadedState(Long messageId);

    ResponseEntity<Void> changeAllChatMessageToReadedState(Long userId, Long chatId);

    ResponseEntity<Boolean> checkForPendingMessages(Authentication auth);

}
