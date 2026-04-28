package com.rubenmartin.calenderyback.publication.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlRequest;
import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlResponse;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.SignedUrlPostDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.mapper.PublicationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/api/publication")
@RequiredArgsConstructor
public class PublicationController implements PublicationRestApi {

    private final Mediator mediator;
    private final PublicationDtoMapper publicationDtoMapper;

    @Override
    public ResponseEntity<Page<PublicationDto>> getByUserAndMonthAndYear(@RequestParam("idUsuario") Long idUsuario, int month, int year, Pageable pageable) {

        return null;
    }

    @Override
    @GetMapping("/app/getPostUrl")
    public ResponseEntity<SignedUrlPostDto> getSignedPostUrl(Authentication authentication) {
        GetPostPublicationSignedUrlRequest postRequest = new GetPostPublicationSignedUrlRequest(authentication.getName());
        GetPostPublicationSignedUrlResponse postResponse = mediator.dispatch(postRequest);

        SignedUrlPostDto responseDto = publicationDtoMapper.mapToSignedPostDto(postResponse);

        return ResponseEntity.ok(responseDto);
    }
}
