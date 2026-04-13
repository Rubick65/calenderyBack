package com.rubenmartin.calenderyback.vertificationToken.application.query.getByToken;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVerificationTokenByTokenRequest implements Request<GetVerificationTokenByTokenResponse> {
    String token;
}
