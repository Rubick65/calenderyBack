package com.rubenmartin.calenderyback.user.application.query.getAll;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUsersHandler implements RequestHandler<GetAllUsersRequest, GetAllUsersResponse> {

    private final UserRepositoryPort userRepository;

    @Override
    public GetAllUsersResponse handle(GetAllUsersRequest request) {
        List<User> users = userRepository.findAllUsers();

        return new GetAllUsersResponse(users);
    }

    @Override
    public Class<GetAllUsersRequest> getRequestType() {
        return GetAllUsersRequest.class;
    }
}
