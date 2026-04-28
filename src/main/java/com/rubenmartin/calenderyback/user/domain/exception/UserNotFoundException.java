package com.rubenmartin.calenderyback.user.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The user with id " + id + " was not found");
    }

    public UserNotFoundException(String email) {
        super("The user with email " + email + " was not found");
    }
}
