package com.rubenmartin.calenderyback.user.domain.port;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User upsertUser(User user);

    void deleteUser(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    boolean accountIsEnabled(Long idUsuario);

    Optional<Long> getUserIdByEmail(String email);

    Page<User> findUserContacts(Long idUsuario, Pageable pageable);

    Page<User> findUserContactsByName(Long idUsuario, String userName, Pageable pageable);

    Optional<String> getUserPublicKey(Long idUsuario);
}
