package com.rubenmartin.calenderyback.publication.infrastructure.apiRest;

import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.SignedUrlPostDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.awt.print.Pageable;

public interface PublicationRestApi {

    ResponseEntity<Page<PublicationDto>> getByUserAndMonthAndYear(Long idUsuario, int month, int year, Pageable pageable);

    ResponseEntity<SignedUrlPostDto> getSignedPostUrl(Authentication authentication);
}
