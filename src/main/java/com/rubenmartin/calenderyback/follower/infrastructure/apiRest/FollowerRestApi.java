package com.rubenmartin.calenderyback.follower.infrastructure.apiRest;

import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserReducedData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FollowerRestApi {
    ResponseEntity<Void> follow(Long userId, Long userToFollowId);

    ResponseEntity<Void> unfollow(Long userId, Long userToUnfollowId);

    ResponseEntity<Page<UserReducedData>> getUserFollowers(Long userId, Pageable pageable);

    ResponseEntity<Page<UserReducedData>> getUserFollowing(Long userId, Pageable pageable);

}
