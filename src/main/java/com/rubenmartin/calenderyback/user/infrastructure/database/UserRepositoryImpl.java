package com.rubenmartin.calenderyback.user.infrastructure.database;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPort {

    private final UserJPARepository userJPARepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User upsertUser(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        return userEntityMapper.mapToUser(userJPARepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userJPARepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userJPARepository.deleteAll();
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> entityUsers = userJPARepository.findAll();

        return entityUsers.stream().map(userEntityMapper::mapToUser).toList();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userJPARepository.findById(id)
                .map(userEntityMapper::mapToUser);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userJPARepository.findUserByEmail(email)
                .map(userEntityMapper::mapToUser);

    }
}
