package com.rubenmartin.calenderyback.follower.application.command.follow;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserHandler implements RequestHandler<FollowUserRequest, Void> {

    private final FollowerRepositoryPort followerRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Void handle(FollowUserRequest request) {
        User followerUser = userRepositoryPort.findUserById(request.getFollowerId()).orElseThrow(() -> new UserNotFoundException(request.getFollowerId()));
        User userToFollow = userRepositoryPort.findUserById(request.getUserToFollowId()).orElseThrow(() -> new UserNotFoundException(request.getUserToFollowId()));

        Follower newFollower =
                Follower.builder()
                        .userFollow(followerUser)
                        .userFollowed(userToFollow)
                        .build();

        followerRepositoryPort.saveSeguidor(newFollower);
        return null;
    }

    @Override
    public Class<FollowUserRequest> getRequestType() {
        return FollowUserRequest.class;
    }
}
