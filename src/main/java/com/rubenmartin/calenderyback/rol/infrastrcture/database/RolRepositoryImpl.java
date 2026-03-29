package com.rubenmartin.calenderyback.rol.infrastrcture.database;


import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.domain.port.RolRepositoryPort;
import com.rubenmartin.calenderyback.rol.infrastrcture.database.mapper.RolEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RolRepositoryImpl implements RolRepositoryPort {

    private final RolJPARepository rolJpaRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public Optional<Rol> findByName(String rol_name) {
        return rolJpaRepository.findByRolName(rol_name).
                map(rolEntityMapper::mapToRol);
    }
}
