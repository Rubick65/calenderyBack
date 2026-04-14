package com.rubenmartin.calenderyback.user.infrastructure.apiRest;

import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface UserRestApi {

    // Devuelve una lista con todos los usuarios existentes
    ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false) String pageSize);

    // Devuelve el usuario con el id introducido si existe
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    ResponseEntity<UserDto> getUserByEmail(@PathVariable String email);


    // Guarda el usuario en la base de datos
    ResponseEntity<Void> registerUser(@RequestBody UserDto user, HttpServletRequest request);

    ResponseEntity<UserResponseDto> confirmRegistration(@RequestBody String token);

    // Actualiza los datos de un usuarios
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto user);

    // Elimina un usuario por id
    ResponseEntity<Void> deleteUserById(@PathVariable Long id);

    // Elimina todos los usuarios
    ResponseEntity<Void> deleteAllUsers();

}
