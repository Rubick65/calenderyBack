package com.rubenmartin.calenderyback.vertificationToken.domain.entity;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    private Long id;
    private String token;
    private User user;
    private Date expiryDate;
}
