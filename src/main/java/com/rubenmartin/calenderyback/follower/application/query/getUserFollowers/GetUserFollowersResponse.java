package com.rubenmartin.calenderyback.follower.application.query.getUserFollowers;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetUserFollowersResponse {
    private Page<User> userFollowers;
}
