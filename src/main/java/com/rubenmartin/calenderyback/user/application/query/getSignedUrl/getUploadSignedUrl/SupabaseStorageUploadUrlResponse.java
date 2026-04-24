package com.rubenmartin.calenderyback.user.application.query.getSignedUrl.getUploadSignedUrl;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupabaseStorageUploadUrlResponse {
    private String url;
}
