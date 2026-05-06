package com.rubenmartin.calenderyback.publication.infrastructure.database;

import com.rubenmartin.calenderyback.publication.infrastructure.database.entity.PublicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicationJPARepository extends JpaRepository<PublicationEntity, Long> {

    @Query("SELECT p FROM PublicationEntity p " +
            "WHERE p.user.idUsuario = :idUsuario " +
            "AND MONTH(p.publicationDate.calendarDate) = :month " +
            "AND YEAR(p.publicationDate.calendarDate) = :year")
    Page<PublicationEntity> findByUserAndMonthAndYear(Long idUsuario,
                                                      int month,
                                                      int year,
                                                      Pageable pageable);

    @Query("SELECT p FROM PublicationEntity p " +
            "WHERE MONTH(p.publicationDate.uploadDate) = :currentMonth " +
            "AND YEAR(p.publicationDate.uploadDate) = YEAR(CURRENT_DATE)" +
            "AND p.user.idUsuario <> :userId")
    Page<PublicationEntity> getCurrentMonthPublications(@Param("currentMonth") int currentMonth, @Param("userId") Long userId, Pageable pageable);
}
