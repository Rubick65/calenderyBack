package com.rubenmartin.calenderyback.chat.application.query.getUserChats;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserChatByUserIdRequest implements Request<GetUserChatByUserResponse> {
    private Long userId;
}
