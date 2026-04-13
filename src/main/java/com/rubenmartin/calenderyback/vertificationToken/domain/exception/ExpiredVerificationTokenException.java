package com.rubenmartin.calenderyback.vertificationToken.domain.exception;

public class ExpiredVerificationTokenException extends RuntimeException {
    public ExpiredVerificationTokenException(String token) {
        super("Token " + token + " is expired");
    }
}
