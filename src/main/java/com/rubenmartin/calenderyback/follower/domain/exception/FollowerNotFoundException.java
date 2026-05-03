package com.rubenmartin.calenderyback.follower.domain.exception;

public class FollowerNotFoundException extends RuntimeException {
    public FollowerNotFoundException() {
        super("Follower not found");
    }
}
