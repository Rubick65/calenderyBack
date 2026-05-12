package com.rubenmartin.calenderyback.follower.application.query.getUserFollowers;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetUserFollowersRequest implements Request<GetUserFollowersResponse> {
    private String userEmail;
    private Pageable pageable;
}
