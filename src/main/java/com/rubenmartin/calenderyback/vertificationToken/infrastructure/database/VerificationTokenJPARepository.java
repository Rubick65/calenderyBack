package com.rubenmartin.calenderyback.vertificationToken.infrastructure.database;


import com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VerificationTokenJPARepository extends JpaRepository<VerificationTokenEntity, Long> {

    Optional<VerificationTokenEntity> findByToken(String token);

    Optional<VerificationTokenEntity> findByUser_IdUsuario(@Param("id") Long id);

}
