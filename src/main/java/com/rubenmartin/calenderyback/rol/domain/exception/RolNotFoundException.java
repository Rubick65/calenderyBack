package com.rubenmartin.calenderyback.rol.domain.exception;

public class RolNotFoundException extends RuntimeException {
    public RolNotFoundException(String role_name) {
        super("Rol with name " + role_name + " was not found");
    }
}
