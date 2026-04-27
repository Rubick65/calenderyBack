package com.rubenmartin.calenderyback.chat.application.query.getChat;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.domain.exception.ChatNotFoundException;
import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetChatByIdHandler implements RequestHandler<GetChatByIdRequest, GetChatByIdResponse> {
    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public GetChatByIdResponse handle(GetChatByIdRequest request) {
        Long chatId = request.getChatId();

        Chat chat = chatRepositoryPort.getChatById(chatId).orElseThrow(() -> new ChatNotFoundException(chatId));

        return new GetChatByIdResponse(chat);
    }

    @Override
    public Class<GetChatByIdRequest> getRequestType() {
        return GetChatByIdRequest.class;
    }
}
