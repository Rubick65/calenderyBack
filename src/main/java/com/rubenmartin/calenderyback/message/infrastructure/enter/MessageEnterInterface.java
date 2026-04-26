package com.rubenmartin.calenderyback.message.infrastructure.enter;

import com.rubenmartin.calenderyback.message.infrastructure.enter.dto.MessageDto;

public interface MessageEnterInterface {

    void saveMessage(MessageDto message);

    MessageDto getMessage(Long id);
}
