package com.rubenmartin.calenderyback.vertificationToken.infrastructure.database;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.TokenNotFoundException;
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

        VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, token);

        verificationTokenJPARepository.save(verificationTokenEntity);

    }

    @Override
    public VerificationToken generateNewVerificationToken(String token) {
        VerificationTokenEntity verificationTokenEntity = verificationTokenJPARepository.findByToken(token).orElseThrow(TokenNotFoundException::new);
        verificationTokenEntity.setExpiryDate(verificationTokenEntity.calculateExpiryDate());

        verificationTokenJPARepository.save(verificationTokenEntity);

        return verificationTokenMapper.toVerificationTokenEntity(verificationTokenEntity);

    }

    @Override
    public Optional<VerificationToken> findByUserId(Long id) {
        return verificationTokenJPARepository.findByUser_IdUsuario(id)
                .map(verificationTokenMapper::toVerificationTokenEntity);
    }

}
