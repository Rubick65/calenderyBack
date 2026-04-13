package com.rubenmartin.calenderyback.vertificationToken.domain.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String token) {
        super("Token " + token + " was not found.");
    }
}
