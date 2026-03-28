package com.rubenmartin.calenderyback.user.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The user with id " + id + " was not found");
    }
}
