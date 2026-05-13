package com.rubenmartin.calenderyback.follower.application.query.getUserFollowers;

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
public class GetUserFollowersHandler implements RequestHandler<GetUserFollowersRequest, GetUserFollowersResponse> {
    private final FollowerRepositoryPort followerRepositoryPort;

    @Override
    public GetUserFollowersResponse handle(GetUserFollowersRequest request) {
        Long userId = request.getUserId();
        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "nombre"));

        Page<User> userFollowers = followerRepositoryPort.getUserFollowers(userId, pageable);
        return new GetUserFollowersResponse(userFollowers);
    }

    @Override
    public Class<GetUserFollowersRequest> getRequestType() {
        return GetUserFollowersRequest.class;
    }
}
