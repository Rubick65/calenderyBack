package com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.mapper;

import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.entity.VerificationTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VerificationTokenMapper {

    VerificationTokenEntity toVerificationTokenEntity(VerificationToken verificationToken);

    VerificationToken toVerificationTokenEntity(VerificationTokenEntity verificationTokenEntity);


}
