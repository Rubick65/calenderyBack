package com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignedUrlPostDto {
    @NotBlank
    private Long idPost;
    @NotBlank
    private String url;
}
