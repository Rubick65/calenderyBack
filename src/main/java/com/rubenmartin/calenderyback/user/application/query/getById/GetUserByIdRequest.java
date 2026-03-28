package com.rubenmartin.calenderyback.user.application.query.getById;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserByIdRequest implements Request<GetUserByIdResponse> {
    private Long id;
}
