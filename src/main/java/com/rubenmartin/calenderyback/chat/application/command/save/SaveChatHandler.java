package com.rubenmartin.calenderyback.chat.application.command.save;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveChatHandler implements RequestHandler<SaveChatRequest, Void> {

    private final ChatRepositoryPort chatRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(SaveChatRequest request) {
        Chat chat = request.getChat();

        Long user1Id = chat.getUser1().getIdUsuario();
        Long user2Id = chat.getUser2().getIdUsuario();

        chat.setUser1(userRepositoryPort.findUserById(user1Id).orElseThrow(() -> new UserNotFoundException(user1Id)));
        chat.setUser2(userRepositoryPort.findUserById(user2Id).orElseThrow(() -> new UserNotFoundException(user2Id)));
        
        chatRepositoryPort.saveChat(chat);
        return null;
    }

    @Override
    public Class<SaveChatRequest> getRequestType() {
        return SaveChatRequest.class;
    }
}
