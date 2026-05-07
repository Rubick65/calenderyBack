package com.rubenmartin.calenderyback.user.application.query.getUserCommentData;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserCommentDataRequest implements Request<GetUserCommentDataResponse> {
    private String userEmail;
}
