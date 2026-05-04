package com.rubenmartin.calenderyback.follower.application.query.isFollowing;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class isFollowingRequest implements Request<isFollowingResponse> {
    private String userEmail;
    private Long possibleFollower;
}
