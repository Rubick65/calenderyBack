package com.rubenmartin.calenderyback.publicationLike.domain.port;

import com.rubenmartin.calenderyback.publicationLike.domain.entity.PublicationLike;

public interface PublicationLikeRepositoryPort {

    PublicationLike saveLike(PublicationLike like);

    int getPublicationLikes(Long publicationId);

    Void deletePublicationLike(Long idPublication, Long idUser);
}
