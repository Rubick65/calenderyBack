package com.rubenmartin.calenderyback.publicationDate.infrastructure.database.entity;

import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fechas_publicacion")
public class PublicationDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fecha_publicacion")
    private Long id;

    @Column(name = "fecha_calendario")
    private LocalDate calendarDate;

    @Column(name = "fecha_subida")
    private LocalDateTime uploadDate;

    @OneToOne
    @JoinColumn(name = "id_publicacion", referencedColumnName = "id_publicacion")
    private PublicationEntity publication;
}
