package com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getReadSignedUrl;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupabaseStorageResponse {
    private String url;
}
