package com.rubenmartin.calenderyback.user.infrastructure.apiRest;

import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    ResponseEntity<UserInfoResponseDto> registerUser(@RequestBody UserDto user, HttpServletRequest request);

    ResponseEntity<Void> confirmRegistration(WebRequest request, @RequestParam("token") String token);

    // Actualiza los datos de un usuarios
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto user);

    // Elimina un usuario por id
    ResponseEntity<Void> deleteUserById(@PathVariable Long id);

    // Elimina todos los usuarios
    ResponseEntity<Void> deleteAllUsers();

    ResponseEntity<Void> activeAccountConfirmation(@RequestParam Long idUsuario);

    ResponseEntity<Void> resendRegistrationToken(@RequestParam Long id);

    ResponseEntity<SupabaseUrlDto> getSignedUrl(Authentication authentication);

    ResponseEntity<UserSettingsResponseDto> getUserSettingsInfo(@RequestParam Long userId);

    ResponseEntity<UserProfileResponseDto> getUserProfileInfo(@RequestParam Long userId, Authentication auth);

    ResponseEntity<Void> updateUserSettings(@RequestParam Long userId, @RequestBody UserSettingsResponseDto userSettingsDto);
    
    ResponseEntity<UserValidationDto> validateUser(@RequestBody String email);
}
