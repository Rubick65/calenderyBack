package com.rubenmartin.calenderyback.message.application.query.getChatMessages;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetChatMessagesByIdRequest implements Request<GetChatMessagesByIdResponse> {
    private Long chatId;
    private Pageable pageable;

}
