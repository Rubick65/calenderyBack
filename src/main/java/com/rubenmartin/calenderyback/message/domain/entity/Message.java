package com.rubenmartin.calenderyback.message.domain.entity;

import com.rubenmartin.calenderyback.chat.domain.entity.Chat;
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
public class Message {
    private Long id;
    private Chat chatId;
    private User fromUser;
    private User toUser;
    private Date timeStamp;
    private String content;
}
