package com.rubenmartin.calenderyback.user.application.command.accountEnabled;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IsUserEnabledResponse {
    private boolean enabled;
}
