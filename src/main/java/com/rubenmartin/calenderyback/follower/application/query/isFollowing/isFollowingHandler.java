package com.rubenmartin.calenderyback.follower.application.query.isFollowing;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class isFollowingHandler implements RequestHandler<isFollowingRequest, isFollowingResponse> {
   private final UserRepositoryPort userRepositoryPort;
   private final FollowerRepositoryPort followerRepositoryPort;

    @Override
    public isFollowingResponse handle(isFollowingRequest request) {
        Long userId = userRepositoryPort.getUserIdByEmail(request.getUserEmail());
        Long possibleFollower = request.getPossibleFollower();

        boolean isFollowing = followerRepositoryPort.isFollowing(userId, possibleFollower);

        return new isFollowingResponse(isFollowing);
    }

    @Override
    public Class<isFollowingRequest> getRequestType() {
        return isFollowingRequest.class;
    }
}
