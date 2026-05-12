package com.rubenmartin.calenderyback.user.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDto {
    private UserEntity user;
    private Long chatId;
}
