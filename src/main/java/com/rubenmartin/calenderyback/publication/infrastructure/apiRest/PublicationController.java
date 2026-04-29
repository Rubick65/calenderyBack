package com.rubenmartin.calenderyback.publication.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.publication.application.command.update.UpdatePublicationRequest;
import com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear.GetByUserAndMonthAndYearRequest;
import com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear.GetByUserAndMonthAndYearResponse;
import com.rubenmartin.calenderyback.publication.application.query.getPublication.GetPublicationByIDRequest;
import com.rubenmartin.calenderyback.publication.application.query.getPublication.GetPublicationByIDResponse;
import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlRequest;
import com.rubenmartin.calenderyback.publication.application.query.getPublicationSignedUrl.getPostPublicationSignedUrl.GetPostPublicationSignedUrlResponse;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PostDataDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.PublicationProfileDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.dto.SignedUrlPostDto;
import com.rubenmartin.calenderyback.publication.infrastructure.apiRest.mapper.PublicationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/publication")
@RequiredArgsConstructor
public class PublicationController implements PublicationRestApi {

    private final Mediator mediator;
    private final PublicationDtoMapper publicationDtoMapper;

    @Override
    @GetMapping("/app/getProfilePosts")
    public ResponseEntity<Page<PublicationProfileDto>> getByUserAndMonthAndYear(@RequestParam("idUsuario") Long idUsuario, @RequestParam("month") int month, @RequestParam("year") int year, Pageable pageable) {
        GetByUserAndMonthAndYearRequest request = new GetByUserAndMonthAndYearRequest(idUsuario, month, year, pageable);
        GetByUserAndMonthAndYearResponse response = mediator.dispatch(request);
        Page<PublicationProfileDto> fileList = response.getPage().map(publicationDtoMapper::mapToPublicationProfile);

        return ResponseEntity.ok(fileList);
    }

    @Override
    @GetMapping("/app/getPostUrl")
    public ResponseEntity<SignedUrlPostDto> getSignedPostUrl(Authentication authentication) {
        GetPostPublicationSignedUrlRequest postRequest = new GetPostPublicationSignedUrlRequest(authentication.getName());
        GetPostPublicationSignedUrlResponse postResponse = mediator.dispatch(postRequest);

        SignedUrlPostDto responseDto = publicationDtoMapper.mapToSignedPostDto(postResponse);

        return ResponseEntity.ok(responseDto);
    }

    @Override
    @PutMapping("/app/putPublicationData")
    public ResponseEntity<Void> putPublicationData(@RequestBody PostDataDto postDataDto) {
        GetPublicationByIDRequest getPublicationRequest = new GetPublicationByIDRequest(postDataDto.getIdPost());
        GetPublicationByIDResponse getPublicationResponse = mediator.dispatch(getPublicationRequest);

        UpdatePublicationRequest updatePublicationRequest = new UpdatePublicationRequest(
                getPublicationResponse.getPublication()
                , postDataDto.getMessage()
                , postDataDto.getIdUsuario()
                , postDataDto.getCalendarDate()
        );

        mediator.dispatch(updatePublicationRequest);

        return ResponseEntity.ok().build();
    }
}
