package com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getReadSignedUrl;


import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupabaseStorageHandler implements RequestHandler<SupabaseStorageRequest, SupabaseStorageResponse> {

    private final GetSignedUrl getSignedUrl;

    @Override
    public SupabaseStorageResponse handle(SupabaseStorageRequest request) {
        String bucketName = request.getBucket();
        String path = request.getFileName();
        String url = getSignedUrl.createStorageSignedUrl(bucketName, path);

        return new SupabaseStorageResponse(url);
    }

    @Override
    public Class<SupabaseStorageRequest> getRequestType() {
        return SupabaseStorageRequest.class;
    }


}
