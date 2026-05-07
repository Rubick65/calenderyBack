package com.rubenmartin.calenderyback.publicationLike.infrastructure.database;

import com.rubenmartin.calenderyback.publicationLike.domain.entity.PublicationLike;
import com.rubenmartin.calenderyback.publicationLike.domain.port.PublicationLikeRepositoryPort;
import com.rubenmartin.calenderyback.publicationLike.infrastructure.database.entity.PublicationLikeEntity;
import com.rubenmartin.calenderyback.publicationLike.infrastructure.database.mapper.PublicationLikeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PublicationLikeRepositoryImpl implements PublicationLikeRepositoryPort {

    private final PublicationLikeJPARepository publicationLikeJPARepository;
    private final PublicationLikeEntityMapper publicationLikeEntityMapper;

    @Override
    public PublicationLike saveLike(PublicationLike like) {
        PublicationLikeEntity publicationLikeEntity = publicationLikeEntityMapper.mapToPublicationLikeEntity(like);
        PublicationLikeEntity savedPublication = publicationLikeJPARepository.save(publicationLikeEntity);

        return publicationLikeEntityMapper.mapToPublicationLike(savedPublication);
    }

    @Override
    public int getPublicationLikes(Long publicationId) {
        return publicationLikeJPARepository.countPublicationLikes(publicationId);
    }

    @Override
    public int deletePublicationLike(Long idPublication, Long idUser) {
        return publicationLikeJPARepository.deletePublicationLike(idPublication, idUser);
    }

    @Override
    public boolean userLiked(Long userId, Long publicationId) {
        return publicationLikeJPARepository.userLiked(userId, publicationId);
    }
}
