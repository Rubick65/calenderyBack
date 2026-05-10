package com.rubenmartin.calenderyback.user.application.query.getSearchUsers;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSearchUsersHandler implements RequestHandler<GetSearchUsersRequest, GetSearchUsersResponse> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public GetSearchUsersResponse handle(GetSearchUsersRequest request) {
        String userName = request.getUserName();
        String userEmail = request.getUserEmail();
        Long userId = userRepositoryPort.getUserIdByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
        Page<User> userContactsPage;

        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "nombre"));

        if (userName == null || userName.isBlank())
            userContactsPage = userRepositoryPort.findSearchUsers(userId, pageable);
        else
            userContactsPage = userRepositoryPort.findSearchUsersByName(userId, userName, pageable);

        return new GetSearchUsersResponse(userContactsPage);
    }

    @Override
    public Class<GetSearchUsersRequest> getRequestType() {
        return GetSearchUsersRequest.class;
    }
}
