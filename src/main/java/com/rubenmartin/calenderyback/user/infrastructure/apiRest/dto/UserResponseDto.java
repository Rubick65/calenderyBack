package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto;

import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    Long idUsuario;
    List<String> roles;

    public UserResponseDto(User user) {
        this.idUsuario = user.getIdUsuario();
        this.roles = user.getRoles().stream().map(Rol::getRolName).toList();
    }

    public UserResponseDto(UserEntity user) {
        this.idUsuario = user.getIdUsuario();
        System.out.println(this.idUsuario);
        this.roles = user.getRoles().stream().map(RolEntity::getRolName).toList();
    }


}
