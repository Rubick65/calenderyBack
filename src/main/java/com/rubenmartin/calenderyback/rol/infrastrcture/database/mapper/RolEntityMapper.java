package com.rubenmartin.calenderyback.rol.infrastrcture.database.mapper;

import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RolEntityMapper {
    Rol mapToRol(RolEntity rolEntity);
}
