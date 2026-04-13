package com.rubenmartin.calenderyback.user.application.command.save;

import com.rubenmartin.calenderyback.common.mediator.Request;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveUserRequest implements Request<Void> {
    User user;
}
