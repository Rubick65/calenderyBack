package com.rubenmartin.calenderyback.follower.infrastructure.database.entity;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "seguidor")
public class FollowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    Long followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seguidor")
    private UserEntity userFollow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_seguido")
    private UserEntity userFollowed;

}
