package com.rubenmartin.calenderyback.follower.infrastructure.database;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.follower.infrastructure.database.entity.FollowerEntity;
import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowerRepositoryImpl implements FollowerRepositoryPort {

    private final FollowerJPARepository followerJPARepository;
    private final FollowerEntityMapper followerEntityMapper;

    @Override
    public Follower saveSeguidor(Follower follower) {
        FollowerEntity followerEntity = followerEntityMapper.mapToFollowerEntity(follower);

        FollowerEntity savedFollower = followerJPARepository.save(followerEntity);

        return followerEntityMapper.mapToFollower(savedFollower);
    }

    @Override
    public int unfollow(Long followerId, Long userToFollowId) {
        return followerJPARepository.unFollow(followerId, userToFollowId);
    }

    @Override
    public Long countFollowers(Long idUsuario) {
        return followerJPARepository.countFollowers(idUsuario);
    }

    @Override
    public Long countFollowed(Long idUsuario) {
        return followerJPARepository.countFollowed(idUsuario);
    }
}
