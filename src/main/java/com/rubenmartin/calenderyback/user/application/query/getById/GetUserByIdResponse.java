package com.rubenmartin.calenderyback.user.application.query.getById;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserByIdResponse {
    User user;
}
