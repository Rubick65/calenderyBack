package com.rubenmartin.calenderyback.message.application.query.getMessage;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import com.rubenmartin.calenderyback.message.domain.exception.MessageNotFoundException;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetMessageByIdHandler implements RequestHandler<GetMessageByIdRequest, GetMessageByIdResponse> {
    private MessageRepositoryPort messageRepository;

    @Override
    public GetMessageByIdResponse handle(GetMessageByIdRequest request) {
        Long id = request.getMessageId();
        Message message = messageRepository.getMessageById(id).orElseThrow(() -> new MessageNotFoundException(id));
        return new GetMessageByIdResponse(message);
    }

    @Override
    public Class<GetMessageByIdRequest> getRequestType() {
        return GetMessageByIdRequest.class;
    }
}
