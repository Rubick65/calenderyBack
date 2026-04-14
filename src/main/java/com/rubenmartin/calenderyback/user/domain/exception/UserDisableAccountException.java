package com.rubenmartin.calenderyback.user.domain.exception;

public class UserDisableAccountException extends RuntimeException {
    public UserDisableAccountException(Long idUsuario) {
        super("User account with  id " + idUsuario + " is disabled");
    }
}
