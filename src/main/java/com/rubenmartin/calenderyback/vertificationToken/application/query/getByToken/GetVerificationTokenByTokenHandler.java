package com.rubenmartin.calenderyback.vertificationToken.application.query.getByToken;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.ExpiredVerificationTokenException;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.TokenNotFoundException;
import com.rubenmartin.calenderyback.vertificationToken.domain.port.VerificationTokenPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GetVerificationTokenByTokenHandler implements RequestHandler<GetVerificationTokenByTokenRequest, GetVerificationTokenByTokenResponse> {

    private final VerificationTokenPort verificationTokenPort;

    @Override
    public GetVerificationTokenByTokenResponse handle(GetVerificationTokenByTokenRequest request) {
        String token = request.getToken();
        Optional<VerificationToken> verificationTokenOpt = verificationTokenPort.findByToken(token);

        if (verificationTokenOpt.isEmpty())
            throw new TokenNotFoundException();

        VerificationToken verificationToken = verificationTokenOpt.get();

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
            throw new ExpiredVerificationTokenException(token);


        return new GetVerificationTokenByTokenResponse(verificationToken);
    }

    @Override
    public Class<GetVerificationTokenByTokenRequest> getRequestType() {
        return GetVerificationTokenByTokenRequest.class;
    }
}
