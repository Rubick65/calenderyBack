package com.rubenmartin.calenderyback.follower.infrastructure.database;

import com.rubenmartin.calenderyback.follower.infrastructure.database.entity.FollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowerJPARepository extends JpaRepository<FollowerEntity, Long> {

    @Query("SELECT COUNT(f) FROM FollowerEntity f WHERE f.userFollowed.idUsuario = :idUsuario")
    Long countFollowers(@Param("idUsuario") Long idUsuario);

    @Query("SELECT COUNT(f) FROM FollowerEntity f WHERE f.userFollow.idUsuario = :idUsuario")
    Long countFollowed(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Query("DELETE FROM FollowerEntity f WHERE f.userFollow.idUsuario = :idUsuario AND f.userFollowed.idUsuario = :userToFollowId")
    int unFollow(@Param("idUsuario") Long followerId, @Param("userToFollowId") Long userToFollowId);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM FollowerEntity f " +
            "WHERE f.userFollow.idUsuario = :idUsuario AND f.userFollowed.idUsuario = :possibleFollower")
    boolean isFollowing(@Param("idUsuario") Long idUsuario, @Param("possibleFollower")Long possibleFollower);
}
