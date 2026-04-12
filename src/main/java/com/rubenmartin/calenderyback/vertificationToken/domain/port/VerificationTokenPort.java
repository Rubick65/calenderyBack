package com.rubenmartin.calenderyback.vertificationToken.domain.port;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;

import java.util.Optional;

public interface VerificationTokenPort {
    Optional<VerificationToken> findByToken(String token);
    void createVerificationToken(UserEntity user, String token);
}
