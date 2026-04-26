package com.rubenmartin.calenderyback.chat.domain.exception;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(Long id) {
        super("Chat with id " + id + " not found");
    }
}
