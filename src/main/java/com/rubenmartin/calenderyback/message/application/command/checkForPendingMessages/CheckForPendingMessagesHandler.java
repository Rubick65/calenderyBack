package com.rubenmartin.calenderyback.message.application.command.checkForPendingMessages;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckForPendingMessagesHandler implements RequestHandler<CheckForPendingMessagesRequest, CheckForPendingMessagesResponse> {
    private final MessageRepositoryPort messageRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public CheckForPendingMessagesResponse handle(CheckForPendingMessagesRequest request) {
        String userEmail = request.getUserEmail();

        Long userId = userRepositoryPort
                .getUserIdByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        boolean pendingMessages = messageRepositoryPort.checkForPendingMessages(userId);
        
        return new CheckForPendingMessagesResponse(pendingMessages);
    }

    @Override
    public Class<CheckForPendingMessagesRequest> getRequestType() {
        return CheckForPendingMessagesRequest.class;
    }
}
