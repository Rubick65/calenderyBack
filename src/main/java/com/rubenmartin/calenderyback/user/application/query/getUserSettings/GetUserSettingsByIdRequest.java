package com.rubenmartin.calenderyback.user.application.query.getUserSettings;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserSettingsByIdRequest implements Request<GetUserSettingsByIdResponse> {
    private Long id;
}
