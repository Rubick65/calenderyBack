package com.rubenmartin.calenderyback.message.application.query.getChatMessages;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetChatMessagesByIdHandler implements RequestHandler<GetChatMessagesByIdRequest, GetChatMessagesByIdResponse> {

    private final MessageRepositoryPort messageRepositoryPort;

    @Override
    public GetChatMessagesByIdResponse handle(GetChatMessagesByIdRequest request) {
        Long chatId = request.getChatId();
        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "timeStamp"));
        Page<Message> messagePage = messageRepositoryPort.getMessages(chatId, pageable);

        return new GetChatMessagesByIdResponse(messagePage);
    }

    @Override
    public Class<GetChatMessagesByIdRequest> getRequestType() {
        return GetChatMessagesByIdRequest.class;
    }
}
