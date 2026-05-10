package com.rubenmartin.calenderyback.message.infrastructure.apiRest;

import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageRestApi {

    ResponseEntity<Page<MessageDto>> getMessages(Long idChat, Pageable pageable);

    ResponseEntity<Void> changeMessageToSendState(Long messageId);

    ResponseEntity<Void> changeMessageToReadedState(Long messageId);
}
