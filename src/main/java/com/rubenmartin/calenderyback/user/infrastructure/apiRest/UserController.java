package com.rubenmartin.calenderyback.user.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.user.application.command.accountEnabled.IsUserEnabledRequest;
import com.rubenmartin.calenderyback.user.application.command.accountEnabled.IsUserEnabledResponse;
import com.rubenmartin.calenderyback.user.application.command.delete.DeleteUserRequest;
import com.rubenmartin.calenderyback.user.application.command.deleteAll.DeleteAllUsersRequest;
import com.rubenmartin.calenderyback.user.application.command.register.RegisterUserRequest;
import com.rubenmartin.calenderyback.user.application.command.register.RegisterUserResponse;
import com.rubenmartin.calenderyback.user.application.command.registrationComplete.OnRegistrationCompleteEvent;
import com.rubenmartin.calenderyback.user.application.command.resendToken.ResendTokenRequest;
import com.rubenmartin.calenderyback.user.application.command.save.SaveUserRequest;
import com.rubenmartin.calenderyback.user.application.command.update.UpdateUserRequest;
import com.rubenmartin.calenderyback.user.application.command.updateUserSettings.UpdateUserSettingsRequest;
import com.rubenmartin.calenderyback.user.application.query.getAll.GetAllUsersRequest;
import com.rubenmartin.calenderyback.user.application.query.getAll.GetAllUsersResponse;
import com.rubenmartin.calenderyback.user.application.query.getByEmail.GetUserByEmailRequest;
import com.rubenmartin.calenderyback.user.application.query.getByEmail.GetUserByEmailResponse;
import com.rubenmartin.calenderyback.user.application.query.getById.GetUserByIdRequest;
import com.rubenmartin.calenderyback.user.application.query.getById.GetUserByIdResponse;
import com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getReadSignedUrl.SupabaseStorageRequest;
import com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getReadSignedUrl.SupabaseStorageResponse;
import com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getUploadSignedUrl.SupabaseStorageUploadUrlRequest;
import com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getUploadSignedUrl.SupabaseStorageUploadUrlResponse;
import com.rubenmartin.calenderyback.user.application.query.getUserProfile.GetUserProfileByIdRequest;
import com.rubenmartin.calenderyback.user.application.query.getUserProfile.GetUserProfileByIdResponse;
import com.rubenmartin.calenderyback.user.application.query.getUserSettings.GetUserSettingsByIdRequest;
import com.rubenmartin.calenderyback.user.application.query.getUserSettings.GetUserSettingsByIdResponse;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.PublicKeyDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto.*;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.mapper.UserMapper;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.mapper.UserEntityMapper;
import com.rubenmartin.calenderyback.vertificationToken.application.query.getByToken.GetVerificationTokenByTokenRequest;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserRestApi {

    private final String PROFILE_PHOTOS_BUCKET = "Avatares";

    private final Mediator mediator;

    private final UserMapper userMapper;
    private final UserEntityMapper userEntityMapper;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Override
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false) String pageSize) {
        GetAllUsersResponse response = mediator.dispatch(new GetAllUsersRequest());

        List<UserDto> usersDto = response.getUsers().stream().map(userMapper::mapToUserDto).toList();

        return ResponseEntity.ok(usersDto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        GetUserByIdResponse response = mediator.dispatch(new GetUserByIdRequest(id));

        UserDto userDto = userMapper.mapToUserDto(response.getUser());

        return ResponseEntity.ok(userDto);
    }

    @Override
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        GetUserByEmailResponse response = mediator.dispatch(new GetUserByEmailRequest(email));

        UserDto userDto = userMapper.mapToUserDto(response.getUser());
        return ResponseEntity.ok(userDto);
    }

    @Override
    @PostMapping("/auth/register")
    public ResponseEntity<UserInfoResponseDto> registerUser(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        RegisterUserRequest userRequest = userMapper.mapToCreateUserRequest(userDto);
        RegisterUserResponse userResponse = mediator.dispatch(userRequest);

        UserEntity registeredUser = userEntityMapper.mapToUserEntity(userResponse.getUser());

        String appUrl = request.getContextPath();

        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser,
                    request.getLocale(), appUrl));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return ResponseEntity.ok(new UserInfoResponseDto(registeredUser));
    }

    @Override
    @GetMapping("/registrationConfirm")
    public ResponseEntity<Void> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        GetVerificationTokenByTokenRequest tokenRequest = new GetVerificationTokenByTokenRequest(token);
        VerificationToken verificationToken = mediator.dispatch(tokenRequest).getVerificationToken();

        User user = verificationToken.getUser();

        user.setEnable(true);

        SaveUserRequest saveUserRequest = new SaveUserRequest(user);

        mediator.dispatch(saveUserRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserInfoResponseDto> login(Authentication authentication) {

        String email = authentication.getName();
        UserDto user = userMapper.mapToUserDto(mediator.dispatch(new GetUserByEmailRequest(email)).getUser());

        return ResponseEntity.ok(new UserInfoResponseDto(user));
    }

    @PutMapping("/app/publicKey")
    @PreAuthorize("#userId == authentication.principal.idUsuario")
    public ResponseEntity<Void> putUserPublicKey(@RequestParam("userId") Long userId, @RequestBody PublicKeyDto publicKey) {
        GetUserByIdResponse response = mediator.dispatch(new GetUserByIdRequest(userId));

        UserDto userDto = userMapper.mapToUserDto(response.getUser());

        userDto.setClavePublica(publicKey.getPublicKey());

        UpdateUserRequest request = userMapper.mapToUpdateUserRequest(userDto);

        mediator.dispatch(request);

        return ResponseEntity.ok().build();
    }


    @Override
    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto user) {
        UpdateUserRequest request = userMapper.mapToUpdateUserRequest(user);

        mediator.dispatch(request);

        return ResponseEntity.noContent().build();
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        mediator.dispatch(new DeleteUserRequest(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllUsers() {
        mediator.dispatch(new DeleteAllUsersRequest());
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/activeAccountConfirmation")
    @PreAuthorize("#idUsuario == authentication.principal.idUsuario")
    public ResponseEntity<Void> activeAccountConfirmation(@RequestParam("idUsuario") Long idUsuario) {
        IsUserEnabledRequest userRequest = new IsUserEnabledRequest(idUsuario);
        mediator.dispatch(userRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/auth/resendRegistrationToken")
    public ResponseEntity<Void> resendRegistrationToken(@RequestParam("idUsuario") Long id) {
        ResendTokenRequest request = new ResendTokenRequest(id);
        mediator.dispatch(request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/app/getUploadProfileSignedUrl")
    public ResponseEntity<SupabaseUrlDto> getSignedUrl(Authentication authentication) {
        String email = authentication.getName();
        GetUserByEmailRequest getUserByEmailRequest = new GetUserByEmailRequest(email);
        UserDto userDto = userMapper.mapToUserDto(mediator.dispatch(getUserByEmailRequest).getUser());

        SupabaseStorageUploadUrlRequest uploadRequest = new SupabaseStorageUploadUrlRequest(PROFILE_PHOTOS_BUCKET, userDto.getIdUsuario());
        SupabaseStorageUploadUrlResponse uploadResponse = mediator.dispatch(uploadRequest);
        SupabaseUrlDto supabaseUrlDto = new SupabaseUrlDto(uploadResponse.getUrl());

        return ResponseEntity.ok(supabaseUrlDto);
    }


    @Override
    @GetMapping("/app/getUserSettings")
    @PreAuthorize("#id == authentication.principal.idUsuario")
    public ResponseEntity<UserSettingsResponseDto> getUserSettingsInfo(@RequestParam("idUsuario") Long id) {
        GetUserSettingsByIdRequest request = new GetUserSettingsByIdRequest(id);
        GetUserSettingsByIdResponse response = mediator.dispatch(request);

        String fileLink = response.getFotoPerfil();

        SupabaseStorageRequest getUrlRequest = new SupabaseStorageRequest(PROFILE_PHOTOS_BUCKET, fileLink);
        SupabaseStorageResponse responseUrl = mediator.dispatch(getUrlRequest);
        
        UserSettingsResponseDto userSetting = userMapper.mapToUserSettingsResponseDto(response, responseUrl.getUrl());

        return ResponseEntity.ok(userSetting);
    }

    @Override
    @GetMapping("/app/getUserProfile")
    public ResponseEntity<UserProfileResponseDto> getUserProfileInfo(@RequestParam("idUsuario") Long id) {
        GetUserProfileByIdRequest request = new GetUserProfileByIdRequest(id);
        GetUserProfileByIdResponse response = mediator.dispatch(request);

        String fileLink = response.getFotoPerfil();

        SupabaseStorageRequest getUrlRequest = new SupabaseStorageRequest(PROFILE_PHOTOS_BUCKET, fileLink);
        SupabaseStorageResponse responseUrl = mediator.dispatch(getUrlRequest);

        UserProfileResponseDto userProfile = userMapper.mapToUserProfileResponseDto(response, responseUrl.getUrl());
        return ResponseEntity.ok(userProfile);

    }

    @Override
    @PutMapping("/app/updateUserSetting")
    @PreAuthorize("#id == authentication.principal.idUsuario")
    public ResponseEntity<Void> updateUserSettings(@RequestParam("idUsuario") Long id, @Valid @RequestBody UserSettingsResponseDto userSettingsDto) {
        UpdateUserSettingsRequest request = userMapper.mapToUpdateUserSettingsRequest(userSettingsDto, id);
        mediator.dispatch(request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/auth/validateUser")
    public ResponseEntity<UserValidationDto> validateUser(@RequestParam("email") String email) {

        GetUserByEmailRequest getUserByEmailRequest = new GetUserByEmailRequest(email);
        UserDto user = userMapper.mapToUserDto(mediator.dispatch(getUserByEmailRequest).getUser());

        IsUserEnabledRequest enable = new IsUserEnabledRequest(user.getIdUsuario());

        IsUserEnabledResponse isUserActive = mediator.dispatch(enable);

        return ResponseEntity.ok(new UserValidationDto(new UserInfoResponseDto(user), isUserActive.isEnabled()));
    }


}
