package com.rubenmartin.calenderyback.chat.infrastructure.apiRest.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatDto {
    private Long id;
    @NotBlank
    private Long user1;
    @NotBlank
    private Long user2;
}
