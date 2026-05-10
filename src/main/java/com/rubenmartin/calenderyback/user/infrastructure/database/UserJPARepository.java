package com.rubenmartin.calenderyback.user.infrastructure.database;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT u.idUsuario FROM UserEntity u WHERE u.email = :email")
    Optional<Long> getUserIdByEmail(@Param("email") String email);

    Optional<UserEntity> findUserByEmail(String email);

    @Query("SELECT DISTINCT u FROM UserEntity u " +
            "WHERE (u.idUsuario IN (" +
            "  SELECT c.user1.idUsuario FROM ChatEntity c WHERE c.user2.idUsuario = :idUsuario" +
            ") OR u.idUsuario IN (" +
            "  SELECT c.user2.idUsuario FROM ChatEntity c WHERE c.user1.idUsuario = :idUsuario" +
            "))")
    Page<UserEntity> finUserContacts(@Param("idUsuario") Long idUsuario, Pageable pageable);

    @Query("SELECT DISTINCT u FROM UserEntity u " +
            "WHERE (u.idUsuario IN (" +
            "  SELECT c.user1.idUsuario FROM ChatEntity c WHERE c.user2.idUsuario = :idUsuario" +
            " AND LOWER(c.user1.nombre) LIKE LOWER(CONCAT('%', :userName, '%'))" +
            ") OR u.idUsuario IN (" +
            "  SELECT c.user2.idUsuario FROM ChatEntity c WHERE c.user1.idUsuario = :idUsuario" +
            " AND LOWER(c.user2.nombre) LIKE LOWER(CONCAT('%', :userName, '%'))" +
            "))")
    Page<UserEntity> findUserContactsByName(@Param("idUsuario") Long idUsuario, @Param("userName") String userName, Pageable pageable);

    @Query("SELECT DISTINCT u FROM UserEntity u " +
            "WHERE u.idUsuario <> :idUsuario")
    Page<UserEntity> findSearchUsers(@Param("idUsuario") Long idUsuario, Pageable pageable);

    @Query("SELECT DISTINCT u FROM UserEntity u " +
            "WHERE (u.idUsuario <> :idUsuario " +
            "AND LOWER(u.nombre) LIKE LOWER(CONCAT('%', :userName, '%')))")
    Page<UserEntity> findSearchUsersByName(@Param("idUsuario") Long idUsuario, @Param("userName") String userName, Pageable pageable);


    @Query("SELECT u.clavePublica FROM UserEntity u WHERE u.idUsuario = :idUsuario")
    Optional<String> getUserPublicKey(@Param("idUsuario") Long idUsuario);

}
