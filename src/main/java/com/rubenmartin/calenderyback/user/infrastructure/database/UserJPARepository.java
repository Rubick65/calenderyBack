package com.rubenmartin.calenderyback.user.infrastructure.database;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio creado automáticamente utilizando JPA
 */
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

}
