package com.rubenmartin.calenderyback.follower.infrastructure.apiRest;

import org.springframework.http.ResponseEntity;

public interface FollowerRestApi {
    ResponseEntity<Void> follow(Long userId, Long userToFollowId);

    ResponseEntity<Void> unfollow(Long userId, Long userToUnfollowId);
}
