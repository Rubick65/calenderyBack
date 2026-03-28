package com.rubenmartin.calenderyback.user.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("User with id " + email + " already exists");
    }
}
