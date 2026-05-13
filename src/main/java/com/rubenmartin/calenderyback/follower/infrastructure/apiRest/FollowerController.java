package com.rubenmartin.calenderyback.follower.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.follower.application.command.follow.FollowUserRequest;
import com.rubenmartin.calenderyback.follower.application.command.unfollow.UnFollowRequest;
import com.rubenmartin.calenderyback.follower.application.query.getUserFollowers.GetUserFollowersRequest;
import com.rubenmartin.calenderyback.follower.application.query.getUserFollowing.GetUserFollowingRequest;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.UserReducedData;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follower")
@RequiredArgsConstructor
public class FollowerController implements FollowerRestApi {
    private final Mediator mediator;
    private final UserMapper userMapper;

    @Override
    @PutMapping("/app/follow")
    @PreAuthorize("#userId == authentication.principal.idUsuario")
    public ResponseEntity<Void> follow(@RequestParam("idUsuario") Long userId, @RequestParam("userToFollowId") Long userToFollowId) {
        FollowUserRequest followRequest = new FollowUserRequest(userId, userToFollowId);
        mediator.dispatch(followRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/app/unfollow")
    @PreAuthorize("#userId == authentication.principal.idUsuario")
    public ResponseEntity<Void> unfollow(@RequestParam("idUsuario") Long userId, @RequestParam("userToUnFollowId") Long userToUnfollowId) {
        UnFollowRequest unFollowRequest = new UnFollowRequest(userId, userToUnfollowId);
        mediator.dispatch(unFollowRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/app/getUserFollowers")
    public ResponseEntity<Page<UserReducedData>> getUserFollowers(Authentication auth, Pageable pageable) {
        GetUserFollowersRequest getUserFollowersRequest = new GetUserFollowersRequest(auth.getName(), pageable);
        Page<UserReducedData> followersPage = mediator.dispatch(getUserFollowersRequest)
                .getUserFollowers()
                .map(userMapper::mapToReduceData);

        return ResponseEntity.ok(followersPage);
    }

    @Override
    @GetMapping("/app/getUserFollowing")
    public ResponseEntity<Page<UserReducedData>> getUserFollowing(Authentication auth, Pageable pageable) {
        GetUserFollowingRequest getUserFollowingRequest = new GetUserFollowingRequest(auth.getName(), pageable);
        Page<UserReducedData> followersPage = mediator.dispatch(getUserFollowingRequest)
                .getUserFollowing()
                .map(userMapper::mapToReduceData);
        
        return ResponseEntity.ok(followersPage);
    }
}
