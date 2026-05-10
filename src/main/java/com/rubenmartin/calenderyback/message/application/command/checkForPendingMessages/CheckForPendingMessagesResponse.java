package com.rubenmartin.calenderyback.message.application.command.checkForPendingMessages;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckForPendingMessagesResponse {
    private boolean pendingMessages;
}
