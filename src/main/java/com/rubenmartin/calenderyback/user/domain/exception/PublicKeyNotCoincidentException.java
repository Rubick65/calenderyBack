package com.rubenmartin.calenderyback.user.domain.exception;

public class PublicKeyNotCoincidentException extends RuntimeException {
    public PublicKeyNotCoincidentException(Long userId) {
        super("User with  " + userId + "id public key doesn't match with parameter public key " );
    }
}
