package com.rubenmartin.calenderyback.publicationLike.infrastructure.apiRest;

import com.rubenmartin.calenderyback.common.mediator.Mediator;
import com.rubenmartin.calenderyback.publicationLike.application.command.delete.DeletePublicationLikeRequest;
import com.rubenmartin.calenderyback.publicationLike.application.command.save.SavePublicationLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class PublicationLikeController implements PublicationLikeRestApi {
    private final Mediator mediator;

    @Override
    @PostMapping("/app/likePublication")
    public ResponseEntity<Void> likePublication(@RequestParam("idPublicacion") Long idPublication, Authentication auth) {
        SavePublicationLikeRequest savePublicationLikeRequest = new SavePublicationLikeRequest(idPublication, auth.getName());
        mediator.dispatch(savePublicationLikeRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    @DeleteMapping("/app/removeLikePublication")
    public ResponseEntity<Void> removeLikePublication(@RequestParam("idPublicacion") Long idPublication, Authentication auth) {
        DeletePublicationLikeRequest deletePublicationLikeRequest = new DeletePublicationLikeRequest(idPublication, auth.getName());
        mediator.dispatch(deletePublicationLikeRequest);
        
        return null;
    }
}
