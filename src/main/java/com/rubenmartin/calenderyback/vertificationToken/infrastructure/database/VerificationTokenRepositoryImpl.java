package com.rubenmartin.calenderyback.vertificationToken.infrastructure.database;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import com.rubenmartin.calenderyback.vertificationToken.domain.port.VerificationTokenPort;
import com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.entity.VerificationTokenEntity;
import com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.mapper.VerificationTokenMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenPort {

    private final VerificationTokenJPARepository verificationTokenJPARepository;
    private final VerificationTokenMapper verificationTokenMapper;

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenJPARepository.findByToken(token)
                .map(verificationTokenMapper::toVerificationTokenEntity);

    }

    @Override
    public void createVerificationToken(UserEntity user, String token) {

        VerificationTokenEntity verificationTokenEntity = VerificationTokenEntity.builder()
                .user(user)
                .token(token)
                .build();

        verificationTokenJPARepository.save(verificationTokenEntity);


    }

}
