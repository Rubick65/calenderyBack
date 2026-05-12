package com.rubenmartin.calenderyback.message.infrastructure.apiRest.dto;

import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageResponseDto {
    private Long idMensaje;
    private String contenido;
    private LocalDateTime timeStamp;
    private EstadoMensaje estadoMensaje;

}
