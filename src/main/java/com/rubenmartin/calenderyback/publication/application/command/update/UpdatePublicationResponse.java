package com.rubenmartin.calenderyback.publication.application.command.update;

import com.rubenmartin.calenderyback.publication.domain.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePublicationResponse {
    private Publication updatedPublication;
}
