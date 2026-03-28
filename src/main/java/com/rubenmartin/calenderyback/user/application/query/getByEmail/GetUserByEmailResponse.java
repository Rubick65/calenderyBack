package com.rubenmartin.calenderyback.user.application.query.getByEmail;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserByEmailResponse {
    User user;
}
