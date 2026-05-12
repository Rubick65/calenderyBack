package com.rubenmartin.calenderyback.chat.application.command.save;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveChatResponse {
    private Chat savedChat;
}
