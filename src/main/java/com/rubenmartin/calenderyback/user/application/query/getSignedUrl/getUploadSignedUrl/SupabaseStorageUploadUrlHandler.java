package com.rubenmartin.calenderyback.user.application.query.getSignedUrl.getUploadSignedUrl;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.exception.UserNotFoundException;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Component
public class SupabaseStorageUploadUrlHandler implements RequestHandler<SupabaseStorageUploadUrlRequest, SupabaseStorageUploadUrlResponse> {

    private final UserRepositoryPort userRepositoryPort;
    @Value("${SUPABASE_URL}")
    private String supabaseUrl;

    @Value("${SERVICE_KEY}")
    private String serviceKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public SupabaseStorageUploadUrlHandler(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public SupabaseStorageUploadUrlResponse handle(SupabaseStorageUploadUrlRequest request) {
        Long userId = request.getUserId();
        User user = userRepositoryPort.findUserById(request.getUserId()).orElseThrow(() -> new UserNotFoundException(userId));

        String bucketName = request.getBucket();
        String path = generateFileName();
        System.out.println(path);


        String url = String.format("%s/storage/v1/object/upload/sign/%s/%s", supabaseUrl, bucketName, path);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + serviceKey);
        headers.set("apikey", serviceKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("expiresIn", 3600);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String partialUrl = (String) response.getBody().get("signedURL");
                System.out.println(response.getBody());

                String completeUrl = supabaseUrl + "/storage/v1" + partialUrl;

                System.out.println(completeUrl + " Entró");

                user.setFotoPerfil(path);

                userRepositoryPort.upsertUser(user);

                return new SupabaseStorageUploadUrlResponse(completeUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<SupabaseStorageUploadUrlRequest> getRequestType() {
        return SupabaseStorageUploadUrlRequest.class;
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
