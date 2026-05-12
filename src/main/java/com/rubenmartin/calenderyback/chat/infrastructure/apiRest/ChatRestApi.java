package com.rubenmartin.calenderyback.chat.infrastructure.apiRest;

import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatDto;
import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatRestApi {

    ResponseEntity<ChatId> saveChat(@RequestBody ChatDto chat);

    ResponseEntity<ChatDto> getChatById(@RequestParam Long id);

    ResponseEntity<List<ChatDto>> getUserChats(@RequestParam Long userId);

    ResponseEntity<ChatId> getChatId(Long userId, Authentication auth);
}
