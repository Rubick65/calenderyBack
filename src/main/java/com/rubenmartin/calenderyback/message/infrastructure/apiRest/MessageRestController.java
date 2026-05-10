package com.rubenmartin.calenderyback.message.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.message.application.query.getChatMessages.GetChatMessagesByIdRequest;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto.MessageDto;
import com.rubenmartin.calenderyback.message.infrastructure.apiRest.mapper.MessageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageRestController implements MessageRestApi {
    private final Mediator mediator;
    private final MessageDtoMapper messageDtoMapper;

    @Override
    @GetMapping("/app/getChatMessages")
    public ResponseEntity<Page<MessageDto>> getMessages(@RequestParam("idChat") Long idChat, Pageable pageable) {
        GetChatMessagesByIdRequest getChatMessages = new GetChatMessagesByIdRequest(idChat, pageable);
        Page<MessageDto> messagePage = mediator.dispatch(getChatMessages).getMessagePage().map(messageDtoMapper::mapToMessageDto);

        return ResponseEntity.ok(messagePage);
    }
}
