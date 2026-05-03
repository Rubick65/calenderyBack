package com.rubenmartin.calenderyback.follower.application.command.follow;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowUserRequest implements Request<Void> {
    private Long followerId;
    private Long userToFollowId;
}
