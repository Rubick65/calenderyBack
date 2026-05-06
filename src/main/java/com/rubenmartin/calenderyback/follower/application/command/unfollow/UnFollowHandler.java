package com.rubenmartin.calenderyback.follower.application.command.unfollow;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.follower.domain.exception.UserNotFollowingException;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UnFollowHandler implements RequestHandler<UnFollowRequest, Void> {
    private final FollowerRepositoryPort followerRepositoryPort;

    @Override
    @Transactional
    public Void handle(UnFollowRequest request) {
        Long followerId = request.getFollowerId();
        Long userToUnFollowId = request.getUserToUnFollowId();
        int deleted = followerRepositoryPort.unfollow(followerId, userToUnFollowId);
        
        if (deleted == 0)
            throw new UserNotFollowingException(followerId, userToUnFollowId);

        return null;
    }

    @Override
    public Class<UnFollowRequest> getRequestType() {
        return UnFollowRequest.class;
    }
}
