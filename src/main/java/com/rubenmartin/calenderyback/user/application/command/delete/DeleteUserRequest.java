package com.rubenmartin.calenderyback.user.application.command.delete;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserRequest implements Request<Void> {
    private Long id;
}
