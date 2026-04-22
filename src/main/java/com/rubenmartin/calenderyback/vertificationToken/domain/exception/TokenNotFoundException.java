package com.rubenmartin.calenderyback.vertificationToken.domain.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Token was not found.");
    }
}
