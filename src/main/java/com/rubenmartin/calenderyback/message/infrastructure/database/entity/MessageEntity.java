package com.rubenmartin.calenderyback.message.infrastructure.database.entity;

import com.rubenmartin.calenderyback.chat.infrastructure.database.entity.ChatEntity;
import com.rubenmartin.calenderyback.message.domain.entity.EstadoMensaje;
import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mensaje_chat")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat", nullable = false)
    private ChatEntity chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_usuario")
    private UserEntity fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "id_receptor")
    private UserEntity toUser;

    @Column(nullable = false, name = "fecha_mensaje")
    private Date timeStamp;

    @Column(nullable = false, name = "contenido")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoMensaje messageState;

}
