package com.rubenmartin.calenderyback.message.infrastructure.enter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private Long id;

    @NotBlank
    private Long chatId;

    @NotBlank
    private Long fromUser;

    @NotBlank
    private Long toUser;
    private Date timeStamp;

    @NotBlank
    private String content;
}
