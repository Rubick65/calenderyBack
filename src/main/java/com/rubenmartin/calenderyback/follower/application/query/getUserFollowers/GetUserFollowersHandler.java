package com.rubenmartin.calenderyback.follower.application.query.getUserFollowers;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserFollowersHandler implements RequestHandler<GetUserFollowersRequest, GetUserFollowersResponse> {
    private UserRepositoryPort userRepositoryPort;
    private FollowerRepositoryPort followerRepositoryPort;

    @Override
    public GetUserFollowersResponse handle(GetUserFollowersRequest request) {
        String userEmail = request.getUserEmail();
        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "nombre"));

        Long userId = userRepositoryPort
                .getUserIdByEmail(userEmail).
                orElseThrow(() -> new UserNotFoundException(userEmail));

        Page<User> userFollowers = followerRepositoryPort.getUserFollowers(userId, pageable);
        return new GetUserFollowersResponse(userFollowers);
    }

    @Override
    public Class<GetUserFollowersRequest> getRequestType() {
        return GetUserFollowersRequest.class;
    }
}
