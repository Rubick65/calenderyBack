package com.rubenmartin.calenderyback.publicationLike.infrastructure.apiRest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PublicationLikeRestApi {
    ResponseEntity<Void> likePublication(Long idPublication, Authentication auth);

    ResponseEntity<Void> removeLikePublication(Long idPublication, Authentication auth);
}
