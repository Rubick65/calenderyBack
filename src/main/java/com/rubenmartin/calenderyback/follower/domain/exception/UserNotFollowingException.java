package com.rubenmartin.calenderyback.follower.domain.exception;

public class UserNotFollowingException extends RuntimeException {
    public UserNotFollowingException(Long followerId, Long userToUnFollowId) {
        super("User with " + followerId + " id dose not follow user with " + userToUnFollowId);
    }
}
