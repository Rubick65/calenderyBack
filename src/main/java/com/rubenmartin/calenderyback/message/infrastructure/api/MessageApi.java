package com.rubenmartin.calenderyback.message.infrastructure.api;

import com.rubenmartin.calenderyback.message.infrastructure.enter.dto.MessageDto;

import java.security.Principal;

public interface MessageApi {

    MessageDto sendMessage(MessageDto messageDto) throws Exception;

    void sendSpecific(MessageDto messageDto, Principal user) throws Exception;

}
