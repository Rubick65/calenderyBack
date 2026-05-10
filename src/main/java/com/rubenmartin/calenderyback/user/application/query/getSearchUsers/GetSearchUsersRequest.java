package com.rubenmartin.calenderyback.user.application.query.getSearchUsers;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetSearchUsersRequest implements Request<GetSearchUsersResponse> {
    private String userName;
    private String userEmail;
    private Pageable pageable;
}
