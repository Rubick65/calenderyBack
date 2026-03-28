package com.rubenmartin.calenderyback.user.application.query.getByEmail;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserByEmailRequest implements Request<GetUserByEmailResponse> {
    String email;
}
