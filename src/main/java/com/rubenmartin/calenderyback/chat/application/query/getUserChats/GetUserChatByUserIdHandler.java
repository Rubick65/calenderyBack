package com.rubenmartin.calenderyback.chat.application.query.getUserChats;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetUserChatByUserIdHandler implements RequestHandler<GetUserChatByUserIdRequest, GetUserChatByUserResponse> {

    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public GetUserChatByUserResponse handle(GetUserChatByUserIdRequest request) {
        Long userId = request.getUserId();

        List<Chat> userChats = chatRepositoryPort.getUserChats(userId).orElse(List.of());
        
        return new GetUserChatByUserResponse(userChats);
    }

    @Override
    public Class<GetUserChatByUserIdRequest> getRequestType() {
        return GetUserChatByUserIdRequest.class;
    }
}
