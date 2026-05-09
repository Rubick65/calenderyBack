package com.rubenmartin.calenderyback.user.application.query.getUserContacts;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.message.domain.port.MessageRepositoryPort;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetUserContactsHandler implements RequestHandler<GetUserContactsRequest, GetUserContactsResponse> {

    private final UserRepositoryPort userRepositoryPort;
    private final MessageRepositoryPort messageRepositoryPort;

    @Override
    public GetUserContactsResponse handle(GetUserContactsRequest request) {
        String userName = request.getUserName();
        String userEmail = request.getUserEmail();
        Long userId = userRepositoryPort.getUserIdByEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
        Page<User> userContactsPage;
        Map<String, String> usersLastMessage = new HashMap<>();

        Pageable pageable = request.getPageable();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "nombre"));

        if (userName == null || userName.isBlank())
            userContactsPage = userRepositoryPort.findUserContacts(userId, pageable);
        else
            userContactsPage = userRepositoryPort.findUserContactsByName(userId, userName, pageable);


        userContactsPage.getContent().forEach(user -> {
            String lastChatMessage = messageRepositoryPort.getLastChatMessage(userId, user.getIdUsuario());
            usersLastMessage.put(user.getNombre(), lastChatMessage);
        });

        return new GetUserContactsResponse(userContactsPage, usersLastMessage);
    }

    @Override
    public Class<GetUserContactsRequest> getRequestType() {
        return GetUserContactsRequest.class;
    }
}
