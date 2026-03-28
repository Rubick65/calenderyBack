package com.rubenmartin.calenderyback.user.application.query.getAll;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllUsersResponse {
    List<User> users;
}
