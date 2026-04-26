package com.rubenmartin.calenderyback.chat.application.command.save;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveChatRequest implements Request<Void> {
    private Chat chat;
}
