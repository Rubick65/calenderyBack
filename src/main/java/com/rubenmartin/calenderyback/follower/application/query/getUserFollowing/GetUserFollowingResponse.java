package com.rubenmartin.calenderyback.follower.application.query.getUserFollowing;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetUserFollowingResponse {
    private Page<User> userFollowing;
}
