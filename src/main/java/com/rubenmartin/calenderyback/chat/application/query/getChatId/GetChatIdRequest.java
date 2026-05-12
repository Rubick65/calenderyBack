package com.rubenmartin.calenderyback.chat.application.query.getChatId;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetChatIdRequest implements Request<GetChatIdResponse> {
    private String userEmail;
    private Long user2Id;
}
