package com.rubenmartin.calenderyback.chat.application.query.getUserChats;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetUserChatByUserResponse {
    private List<Chat> userChats;
}
