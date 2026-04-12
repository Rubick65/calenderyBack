package com.rubenmartin.calenderyback.user.application.command.registrationComplete;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UserEntity user;

    public OnRegistrationCompleteEvent(
            UserEntity user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

}
