package com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getUploadSignedUrl;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupabaseStorageUploadUrlResponse {
    private String url;
}
