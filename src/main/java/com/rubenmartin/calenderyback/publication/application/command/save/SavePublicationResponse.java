package com.rubenmartin.calenderyback.publication.application.command.save;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePublicationResponse {
    private Publication savedPublication;
}
