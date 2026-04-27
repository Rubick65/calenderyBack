package com.rubenmartin.calenderyback.chat.application.query.getChat;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetChatByIdRequest implements Request<GetChatByIdResponse> {
    private Long chatId;
}
