package com.rubenmartin.calenderyback.follower.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.follower.application.command.follow.FollowUserRequest;
import com.rubenmartin.calenderyback.follower.application.command.unfollow.UnFollowRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follower")
@RequiredArgsConstructor
public class FollowerController implements FollowerRestApi {
    private final Mediator mediator;

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
}
