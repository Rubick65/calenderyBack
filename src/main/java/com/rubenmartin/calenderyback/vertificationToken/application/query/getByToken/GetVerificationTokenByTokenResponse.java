package com.rubenmartin.calenderyback.vertificationToken.application.query.getByToken;

import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVerificationTokenByTokenResponse {
    VerificationToken verificationToken;
}
