package com.rubenmartin.calenderyback.publication.domain.port;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicationRepositoryPort {

    Page<Publication> findUserPublications(Long idUsuario,
                                           int month,
                                           int year,
                                           Pageable pageable);

    Publication savePublication(Publication publication);


}
