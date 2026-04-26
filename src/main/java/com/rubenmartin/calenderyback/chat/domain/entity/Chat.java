package com.rubenmartin.calenderyback.chat.domain.entity;

import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private Long id;
    private User user1;
    private User user2;
}
