package com.rubenmartin.calenderyback.chat.application.query.getChat;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetChatByIdResponse {
    private Chat chat;
}
