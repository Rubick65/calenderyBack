package com.rubenmartin.calenderyback.user.domain.port;

import com.rubenmartin.calenderyback.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    void upsertUser(User user);

    void deleteUser(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);


}
