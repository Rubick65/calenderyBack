package com.rubenmartin.calenderyback.publicationDate.domain.entity;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDate {
    private Long id;
    private Publication publication;
    private LocalDate calendarDate;
    private LocalDateTime uploadDate;
}
