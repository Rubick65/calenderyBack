package com.rubenmartin.calenderyback.chat.application.query.checkIfChatExists;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckIfChatExistsRequest implements Request<CheckIfChatExistsResponse> {
    private String userEmail;
    private Long userToCheckId;
}
