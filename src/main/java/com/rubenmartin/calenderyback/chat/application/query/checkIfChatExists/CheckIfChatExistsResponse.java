package com.rubenmartin.calenderyback.chat.application.query.checkIfChatExists;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckIfChatExistsResponse {
    private boolean chatExists;
}
