package com.rubenmartin.calenderyback.rol.infrastrcture.database;

import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface RolJPARepository extends JpaRepository<RolEntity, Long> {

    @Query("SELECT r FROM RolEntity r WHERE r.rolName = :rolName")
    Optional<RolEntity> findByRolName(@Param("rolName") String rolName);
}
