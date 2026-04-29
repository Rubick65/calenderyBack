package com.rubenmartin.calenderyback.publication.infrastructure.database;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import com.rubenmartin.calenderyback.publication.domain.port.PublicationRepositoryPort;
import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import com.rubenmartin.calenderyback.publication.infrastructure.database.mapper.PublicationEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PublicationRepositoryImpl implements PublicationRepositoryPort {

    private final PublicationJPARepository publicationJPARepository;
    private final PublicationEntityMapper publicationEntityMapper;

    @Override
    public Page<Publication> findUserPublications(Long idUsuario, int month, int year, Pageable pageable) {
        Page<PublicationEntity> entityPage = publicationJPARepository.findByUserAndMonthAndYear(idUsuario, month, year, pageable);

        return entityPage.map(publicationEntityMapper::mapToPublication);
    }

    @Override
    public Publication savePublication(Publication publication) {
        PublicationEntity savedPublication = publicationJPARepository.save(publicationEntityMapper.mapToPublicationEntity(publication));

        return publicationEntityMapper.mapToPublication(savedPublication);
    }

    @Override
    public Optional<Publication> getPublicationByID(Long id) {
        return publicationJPARepository.findById(id)
                .map(publicationEntityMapper::mapToPublication);
    }
}
