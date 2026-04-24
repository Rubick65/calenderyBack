package com.rubenmartin.calenderyback.user.application.query.getSignedUrl.getUploadSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupabaseStorageUploadUrlRequest implements Request<SupabaseStorageUploadUrlResponse> {
    private String bucket;
    private Long userId;
}