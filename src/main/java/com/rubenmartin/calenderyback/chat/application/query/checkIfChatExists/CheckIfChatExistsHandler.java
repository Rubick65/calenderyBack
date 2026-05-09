package com.rubenmartin.calenderyback.chat.application.query.checkIfChatExists;

import com.rubenmartin.calenderyback.chat.domain.port.ChatRepositoryPort;
import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckIfChatExistsHandler implements RequestHandler<CheckIfChatExistsRequest, CheckIfChatExistsResponse> {
    private final ChatRepositoryPort chatRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public CheckIfChatExistsResponse handle(CheckIfChatExistsRequest request) {
        Long idUsuario = userRepositoryPort.getUserIdByEmail(request.getUserEmail()).orElseThrow(() -> new UserNotFoundException(request.getUserEmail()));
        boolean chatExists = chatRepositoryPort.checkIfChatExists(idUsuario, request.getUserToCheckId());
        return new CheckIfChatExistsResponse(chatExists);
    }

    @Override
    public Class<CheckIfChatExistsRequest> getRequestType() {
        return CheckIfChatExistsRequest.class;
    }
}
