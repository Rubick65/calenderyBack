package com.rubenmartin.calenderyback.follower.application.query.getUserFollowing;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserFollowingHandler implements RequestHandler<GetUserFollowingRequest, GetUserFollowingResponse> {
    private final FollowerRepositoryPort followerRepositoryPort;

    @Override
    public GetUserFollowingResponse handle(GetUserFollowingRequest request) {
        Long userId = request.getUserId();
        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "nombre"));
        
        Page<User> followingUsers = followerRepositoryPort.getUserFollowing(userId, pageable);

        return new GetUserFollowingResponse(followingUsers);
    }

    @Override
    public Class<GetUserFollowingRequest> getRequestType() {
        return GetUserFollowingRequest.class;
    }
}
