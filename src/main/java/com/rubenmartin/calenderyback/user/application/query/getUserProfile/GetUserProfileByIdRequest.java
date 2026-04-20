package com.rubenmartin.calenderyback.user.application.query.getUserProfile;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserProfileByIdRequest implements Request<GetUserProfileByIdResponse> {
    Long userId;
}
