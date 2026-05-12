package com.rubenmartin.calenderyback.chat.infrastructure.apiRest;

import com.rubenmartin.calenderyback.chat.application.command.save.SaveChatRequest;
import com.rubenmartin.calenderyback.chat.application.query.getChat.GetChatByIdRequest;
import com.rubenmartin.calenderyback.chat.application.query.getChat.GetChatByIdResponse;
import com.rubenmartin.calenderyback.chat.application.query.getChatId.GetChatIdRequest;
import com.rubenmartin.calenderyback.chat.application.query.getUserChats.GetUserChatByUserIdRequest;
import com.rubenmartin.calenderyback.chat.application.query.getUserChats.GetUserChatByUserResponse;
import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatDto;
import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity.ChatId;
import com.rubenmartin.calenderyback.chat.infrastructure.apiRest.mapper.ChatDtoMapper;
import com.rubenmartin.calenderyback.common.mediator.Mediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController implements ChatRestApi {
    private final Mediator mediator;
    private final ChatDtoMapper chatDtoMapper;

    @Override
    @PostMapping("/saveChat")
    public ResponseEntity<ChatId> saveChat(@RequestBody ChatDto chat) {
        SaveChatRequest saveChatRequest = new SaveChatRequest(chatDtoMapper.mapToChat(chat));
        Long chatId = mediator.dispatch(saveChatRequest).getSavedChat().getId();
        
        return ResponseEntity.ok(new ChatId(chatId));
    }

    @Override
    @GetMapping("/getChat")
    public ResponseEntity<ChatDto> getChatById(@RequestParam("idChat") Long id) {
        GetChatByIdRequest request = new GetChatByIdRequest(id);
        GetChatByIdResponse response = mediator.dispatch(request);
        ChatDto chatDto = chatDtoMapper.mapToChatDto(response.getChat());

        return ResponseEntity.ok(chatDto);
    }

    @Override
    @GetMapping("/getUserChats")
    public ResponseEntity<List<ChatDto>> getUserChats(@RequestParam("idUsuario") Long userId) {
        GetUserChatByUserIdRequest request = new GetUserChatByUserIdRequest(userId);

        GetUserChatByUserResponse response = mediator.dispatch(request);

        List<ChatDto> userChats = response.getUserChats().stream()
                .map(chatDtoMapper::mapToChatDto)
                .toList();

        return ResponseEntity.ok(userChats);
    }

    @Override
    @GetMapping("/getUserChat")
    public ResponseEntity<ChatId> getChatId(@RequestParam("idUsuario") Long user2Id, Authentication auth) {
        GetChatIdRequest getChatIdRequest = new GetChatIdRequest(auth.getName(), user2Id);
        Long chatId = mediator.dispatch(getChatIdRequest).getChatId();

        return ResponseEntity.ok(new ChatId(chatId));
    }
}
