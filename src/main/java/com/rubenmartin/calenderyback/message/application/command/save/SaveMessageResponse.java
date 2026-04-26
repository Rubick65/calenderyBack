package com.rubenmartin.calenderyback.message.application.command.save;

import com.rubenmartin.calenderyback.message.domain.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveMessageResponse {
    private Message message;
}
