package com.rubenmartin.calenderyback.message.application.command.checkForPendingMessages;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckForPendingMessagesRequest implements Request<CheckForPendingMessagesResponse> {
    private String userEmail;
}
