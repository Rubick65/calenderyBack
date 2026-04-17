package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto;

import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.rol.infrastrcture.database.entity.RolEntity;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseDto {
    Long idUsuario;
    List<String> roles;

    public UserInfoResponseDto(User user) {
        this.idUsuario = user.getIdUsuario();
        this.roles = user.getRoles().stream().map(Rol::getRolName).toList();
    }

    public UserInfoResponseDto(UserEntity user) {
        this.idUsuario = user.getIdUsuario();
        this.roles = user.getRoles().stream().map(RolEntity::getRolName).toList();
    }


}
