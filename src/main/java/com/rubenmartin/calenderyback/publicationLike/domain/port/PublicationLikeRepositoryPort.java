package com.rubenmartin.calenderyback.publicationLike.domain.port;

import com.rubenmartin.calenderyback.publicationLike.domain.entity.PublicationLike;

public interface PublicationLikeRepositoryPort {

    PublicationLike saveLike(PublicationLike like);

    int getPublicationLikes(Long publicationId);

    int deletePublicationLike(Long idPublication, Long idUser);

    boolean userLiked(Long userId, Long publicationId);

}
