package com.rubenmartin.calenderyback.user.application.query.getSearchUsers;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetSearchUsersResponse {
    private Page<User> searchUserPage;
}
