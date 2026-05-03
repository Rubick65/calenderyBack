package com.rubenmartin.calenderyback.follower.domain.entity;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    Long followerId;

    // Usuario que ha pulsado en seguir
    User userFollow;
    // Usario al cual se va a seguir
    User userFollowed;
}
