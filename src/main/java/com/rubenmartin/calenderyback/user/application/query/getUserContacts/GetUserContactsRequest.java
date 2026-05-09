package com.rubenmartin.calenderyback.user.application.query.getUserContacts;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetUserContactsRequest implements Request<GetUserContactsResponse> {
    private String userName;
    private String userEmail;
    private Pageable pageable;
}
