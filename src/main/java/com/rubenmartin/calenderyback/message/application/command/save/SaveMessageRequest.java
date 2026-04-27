package com.rubenmartin.calenderyback.message.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.message.domain.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SaveMessageRequest implements Request<SaveMessageResponse> {
    private Message message;
    private Date timeStamp;
}
