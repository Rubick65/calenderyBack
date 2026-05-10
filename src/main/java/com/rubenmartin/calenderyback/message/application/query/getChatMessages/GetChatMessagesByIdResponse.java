package com.rubenmartin.calenderyback.message.application.query.getChatMessages;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetChatMessagesByIdResponse {
    private Page<Message> messagePage;
}
