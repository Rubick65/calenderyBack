package com.rubenmartin.calenderyback.user.application.query.getSignedUrl;


import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
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
public class SupabaseStorageHandler implements RequestHandler<SupabaseStorageRequest, SupabaseStorageResponse> {


    @Value("${SUPABASE_URL}")
    private String supabaseUrl;

    @Value("${SERVICE_KEY}")
    private String serviceKey;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public SupabaseStorageResponse handle(SupabaseStorageRequest request) {
        String bucketName = request.getBucket();
        String path = "Perfil_defecto.png";

        String url = String.format("%s/storage/v1/object/sign/%s/%s", supabaseUrl, bucketName, path);

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
                String completeUrl = supabaseUrl + "/storage/v1" + partialUrl;

                return new SupabaseStorageResponse(completeUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Class<SupabaseStorageRequest> getRequestType() {
        return SupabaseStorageRequest.class;
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
