package com.rubenmartin.calenderyback.message.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveMessageHandler implements RequestHandler<SaveMessageRequest, SaveMessageResponse> {

    private MessageRepositoryPort messageRepository;
    private UserRepositoryPort userRepository;

    @Override
    public SaveMessageResponse handle(SaveMessageRequest request) {
        Message message = request.getMessage();

        Long fromUserId = message.getFromUser().getIdUsuario();
        Long toUserId = message.getToUser().getIdUsuario();

        message.setTimeStamp(request.getTimeStamp());
        message.setFromUser(userRepository.findUserById(fromUserId).orElseThrow(() -> new UserNotFoundException(fromUserId)));
        message.setToUser(userRepository.findUserById(toUserId).orElseThrow(() -> new UserNotFoundException(toUserId)));

        Message savedMessage = messageRepository.saveMessage(message).orElse(null);

        return new SaveMessageResponse(savedMessage);
    }

    @Override
    public Class<SaveMessageRequest> getRequestType() {
        return SaveMessageRequest.class;
    }
}
