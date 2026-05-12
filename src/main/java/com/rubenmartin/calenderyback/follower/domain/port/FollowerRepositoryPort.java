package com.rubenmartin.calenderyback.follower.domain.port;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowerRepositoryPort {
    Follower saveSeguidor(Follower follower);

    int unfollow(Long followerId, Long userToFollowId);

    Long countFollowers(Long idUsuario);

    Long countFollowed(Long idUsuario);

    boolean isFollowing(Long idUsuario, Long possibleFollower);

    Page<User> getUserFollowers(Long usedId, Pageable pageable);

    Page<User> getUserFollowing(Long usedId, Pageable pageable);

}
