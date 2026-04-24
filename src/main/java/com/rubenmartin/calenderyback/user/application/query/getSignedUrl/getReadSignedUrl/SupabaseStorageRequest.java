package com.rubenmartin.calenderyback.user.application.query.getSignedUrl.getReadSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupabaseStorageRequest implements Request<SupabaseStorageResponse> {
    private String bucket;
    private String fileName;
}
