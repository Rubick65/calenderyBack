package com.rubenmartin.calenderyback.user.domain.exception;

public class PublicKeyNotFoundException extends RuntimeException {
    public PublicKeyNotFoundException(Long userId) {
        super("User with " + userId + " dose not have a public key");
    }
}
