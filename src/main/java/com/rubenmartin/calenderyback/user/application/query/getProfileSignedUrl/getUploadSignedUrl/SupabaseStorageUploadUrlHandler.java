package com.rubenmartin.calenderyback.user.application.query.getProfileSignedUrl.getUploadSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.common.supabase.GetSignedUrl;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupabaseStorageUploadUrlHandler implements RequestHandler<SupabaseStorageUploadUrlRequest, SupabaseStorageUploadUrlResponse> {

    private final UserRepositoryPort userRepositoryPort;
    private final GetSignedUrl getSignedUrl;

    @Override
    public SupabaseStorageUploadUrlResponse handle(SupabaseStorageUploadUrlRequest request) {
        Long userId = request.getUserId();
        User user = userRepositoryPort.findUserById(request.getUserId()).orElseThrow(() -> new UserNotFoundException(userId));

        String bucketName = request.getBucket();
        String path = generateFileName();

        String url = getSignedUrl.createUploadSignedUrl(bucketName, path);

        user.setFotoPerfil(getSignedUrl.createPublicUrl(bucketName, path));

        userRepositoryPort.upsertUser(user);

        return new SupabaseStorageUploadUrlResponse(url);
    }

    @Override
    public Class<SupabaseStorageUploadUrlRequest> getRequestType() {
        return SupabaseStorageUploadUrlRequest.class;
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
