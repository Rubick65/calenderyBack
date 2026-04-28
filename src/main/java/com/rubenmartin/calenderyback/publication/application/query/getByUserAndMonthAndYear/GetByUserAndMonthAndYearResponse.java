package com.rubenmartin.calenderyback.publication.application.query.getByUserAndMonthAndYear;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class GetByUserAndMonthAndYearResponse {
    private Page<Publication> page;
}
