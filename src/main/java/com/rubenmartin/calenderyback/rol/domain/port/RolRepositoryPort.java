package com.rubenmartin.calenderyback.rol.domain.port;

import com.rubenmartin.calenderyback.rol.domain.entity.Rol;

import java.util.Optional;

public interface RolRepositoryPort {
    Optional<Rol> findByName(String rol_name);
}
