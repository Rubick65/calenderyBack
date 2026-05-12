package com.rubenmartin.calenderyback.chat.application.query.getChatId;

import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetChatIdHandler implements RequestHandler<GetChatIdRequest, GetChatIdResponse> {
    private final ChatRepositoryPort chatRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetChatIdResponse handle(GetChatIdRequest request) {
        String user1Email = request.getUserEmail();
        Long user2Id = request.getUser2Id();

        Long user1Id = userRepositoryPort
                .getUserIdByEmail(user1Email)
                .orElseThrow(() -> new UserNotFoundException(user1Email));

        Long chatId = chatRepositoryPort.getChatId(user1Id, user2Id);
        
        return new GetChatIdResponse(chatId);
    }

    @Override
    public Class<GetChatIdRequest> getRequestType() {
        return GetChatIdRequest.class;
    }
}
