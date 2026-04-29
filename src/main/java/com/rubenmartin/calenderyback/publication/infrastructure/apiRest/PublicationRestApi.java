package com.rubenmartin.calenderyback.publication.infrastructure.apiRest;

import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PostDataDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationProfileDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.SignedUrlPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PublicationRestApi {

    ResponseEntity<Page<PublicationProfileDto>> getByUserAndMonthAndYear(Long idUsuario, int month, int year, Pageable pageable);

    ResponseEntity<SignedUrlPostDto> getSignedPostUrl(Authentication authentication);

    ResponseEntity<Void> putPublicationData(PostDataDto postDataDto);
}
