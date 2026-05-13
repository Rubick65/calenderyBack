package com.rubenmartin.calenderyback.follower.application.query.getUserFollowing;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetUserFollowingRequest implements Request<GetUserFollowingResponse> {
    private String userEmail;
    private Pageable pageable;
}
