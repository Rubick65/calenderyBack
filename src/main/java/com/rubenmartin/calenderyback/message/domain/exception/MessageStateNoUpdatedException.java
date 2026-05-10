package com.rubenmartin.calenderyback.message.domain.exception;

public class MessageStateNoUpdatedException extends RuntimeException {
    public MessageStateNoUpdatedException(Long messageId) {
        super("Message with " + messageId + " state could not be changed");
    }
}
