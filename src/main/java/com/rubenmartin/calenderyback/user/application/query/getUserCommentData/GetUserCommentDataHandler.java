package com.rubenmartin.calenderyback.user.application.query.getUserCommentData;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserCommentDataHandler implements RequestHandler<GetUserCommentDataRequest, GetUserCommentDataResponse> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetUserCommentDataResponse handle(GetUserCommentDataRequest request) {
        String userEmail = request.getUserEmail();

        User user = userRepositoryPort.findUserByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));

        return new GetUserCommentDataResponse(user.getNombre(), user.getFotoPerfil());
    }

    @Override
    public Class<GetUserCommentDataRequest> getRequestType() {
        return GetUserCommentDataRequest.class;
    }
}
