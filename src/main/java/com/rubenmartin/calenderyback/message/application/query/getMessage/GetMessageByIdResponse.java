package com.rubenmartin.calenderyback.message.application.query.getMessage;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMessageByIdResponse {
    private Message message;
}
