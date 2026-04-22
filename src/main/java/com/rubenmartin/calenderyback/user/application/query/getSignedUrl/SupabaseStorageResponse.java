package com.rubenmartin.calenderyback.user.application.query.getSignedUrl;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupabaseStorageResponse {
    private String url;
}
