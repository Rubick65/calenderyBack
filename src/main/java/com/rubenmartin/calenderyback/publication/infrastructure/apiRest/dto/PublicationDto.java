package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublicationDto {
    private Long id;
    
    @NotBlank
    private Long userId;

    private String publicationFileName;
    private String message;
    private int commentaryAmount;
    private int likesAmount;
}
