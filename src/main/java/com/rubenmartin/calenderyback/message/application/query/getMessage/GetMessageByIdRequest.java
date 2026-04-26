package com.rubenmartin.calenderyback.message.application.query.getMessage;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMessageByIdRequest implements Request<GetMessageByIdResponse> {
    private Long messageId;
}
