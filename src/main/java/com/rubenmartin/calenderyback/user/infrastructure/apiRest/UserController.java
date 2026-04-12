package com.rubenmartin.calenderyback.user.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.user.application.command.register.RegisterUserRequest;
import com.rubenmartin.calenderyback.user.application.command.delete.DeleteUserRequest;
import com.rubenmartin.calenderyback.user.application.command.deleteAll.DeleteAllUsersRequest;
import com.rubenmartin.calenderyback.user.application.command.register.RegisterUserResponse;
import com.rubenmartin.calenderyback.user.application.command.update.UpdateUserRequest;
import com.rubenmartin.calenderyback.user.application.query.getAll.GetAllUsersRequest;
import com.rubenmartin.calenderyback.user.application.query.getAll.GetAllUsersResponse;
import com.rubenmartin.calenderyback.user.application.query.getByEmail.GetUserByEmailRequest;
import com.rubenmartin.calenderyback.user.application.query.getByEmail.GetUserByEmailResponse;
import com.rubenmartin.calenderyback.user.application.query.getById.GetUserByIdRequest;
import com.rubenmartin.calenderyback.user.application.query.getById.GetUserByIdResponse;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.application.command.registrationComplete.OnRegistrationCompleteEvent;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.UserDto;
import com.rubenmartin.calenderyback.user.infrastructure.apiRest.mapper.UserMapper;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.user.infrastructure.database.mapper.UserEntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserRestApi {

    private final Mediator mediator;

    private final UserMapper userMapper;
    private final UserEntityMapper userEntityMapper;

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
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        RegisterUserRequest userRequest = userMapper.mapToCreateUserRequest(userDto);
        RegisterUserResponse userResponse = mediator.dispatch(userRequest);

        UserEntity registeredUser = userEntityMapper.mapToUserEntity(userResponse.getUser());

        String appUrl = request.getContextPath();

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser,
                request.getLocale(), appUrl));

        return ResponseEntity.created(URI.create("/api/v1/users".concat(userDto.getEmail()))).build();
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
}
