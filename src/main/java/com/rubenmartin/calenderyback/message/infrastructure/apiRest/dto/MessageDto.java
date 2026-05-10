package com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto;

import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;

    @NotBlank
    private Long chatId;

    @NotBlank
    private Long fromUser;

    @NotBlank
    private Long toUser;
    private LocalDateTime timeStamp;

    @NotBlank
    private String content;

    @NotBlank
    private String selfMessage;

    private EstadoMensaje messageState;
}
