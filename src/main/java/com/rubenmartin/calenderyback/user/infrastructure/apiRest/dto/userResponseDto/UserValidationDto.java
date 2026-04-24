package com.rubenmartin.calenderyback.user.infrastructure.apiRest.dto.userResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserValidationDto {

    private UserInfoResponseDto user;
    private boolean enable;
}
