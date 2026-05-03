package com.rubenmartin.calenderyback.follower.application.command.unfollow;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnFollowRequest implements Request<Void> {
    private Long followerId;
    private Long userToUnFollowId;
}
