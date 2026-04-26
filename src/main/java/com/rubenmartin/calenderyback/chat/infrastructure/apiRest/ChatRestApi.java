package com.rubenmartin.calenderyback.chat.infrastructure.apiRest;

import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatRestApi {

    ResponseEntity<Void> saveChat(@RequestBody ChatDto chat);

    ResponseEntity<ChatDto> getChatById(@RequestParam Long id);

    ResponseEntity<List<ChatDto>> getUserChats(@RequestParam Long userId);
}
