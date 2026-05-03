package com.rubenmartin.calenderyback.user.infrastructure.database;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repositorio creado automáticamente utilizando JPA
 */
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u.enable FROM UserEntity u WHERE u.idUsuario = :idUsuario")
    Boolean accountIsEnabled(@Param("idUsuario") Long idUsuario);

    Optional<UserEntity> findUserByEmail(String email);
}
