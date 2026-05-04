package com.rubenmartin.calenderyback.follower.domain.port;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;

public interface FollowerRepositoryPort {
    Follower saveSeguidor(Follower follower);

    int unfollow(Long followerId, Long userToFollowId);

    Long countFollowers(Long idUsuario);

    Long countFollowed(Long idUsuario);

    boolean isFollowing(Long idUsuario, Long possibleFollower);
}
