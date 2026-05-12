package com.rubenmartin.calenderyback.follower.infrastructure.database;

import com.rubenmartin.calenderyback.follower.domain.entity.Follower;
import com.rubenmartin.calenderyback.follower.domain.port.FollowerRepositoryPort;
import com.rubenmartin.calenderyback.follower.infrastructure.database.entity.FollowerEntity;
import com.rubenmartin.calenderyback.follower.infrastructure.database.mapper.FollowerEntityMapper;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowerRepositoryImpl implements FollowerRepositoryPort {

    private final FollowerJPARepository followerJPARepository;
    private final FollowerEntityMapper followerEntityMapper;
    private final UserEntityMapper userEntityMapper;
    
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

    @Override
    public boolean isFollowing(Long idUsuario, Long possibleFollower) {
        return followerJPARepository.isFollowing(idUsuario, possibleFollower);
    }

    @Override
    public Page<User> getUserFollowers(Long usedId, Pageable pageable) {
        return followerJPARepository
                .getUserFollowers(usedId, pageable)
                .map(userEntityMapper::mapToUser);
    }

    @Override
    public Page<User> getUserFollowing(Long usedId, Pageable pageable) {
        return followerJPARepository
                .getUserFollowing(usedId, pageable)
                .map(userEntityMapper::mapToUser);
    }
}
