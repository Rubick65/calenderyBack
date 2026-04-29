package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GetByUserAndMonthAndYearRequest implements Request<GetByUserAndMonthAndYearResponse> {
    private Long idUsuario;
    private int month;
    private int year;
    private Pageable pageable;
}
