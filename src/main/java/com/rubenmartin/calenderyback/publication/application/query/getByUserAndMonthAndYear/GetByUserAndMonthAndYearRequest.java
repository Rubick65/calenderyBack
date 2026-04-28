package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.common.mediator.Request;
import org.springframework.data.domain.Pageable;

public class GetByUserAndMonthAndYearRequest implements Request<GetByUserAndMonthAndYearResponse> {
    Long idUsuario;
    int month;
    int year;
    Pageable pageable;
}
