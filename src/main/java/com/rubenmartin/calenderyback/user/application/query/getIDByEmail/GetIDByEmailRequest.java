package com.rubenmartin.calenderyback.user.application.query.getIDByEmail;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetIDByEmailRequest implements Request<GetIDByEmailResponse> {
    private String email;
}
