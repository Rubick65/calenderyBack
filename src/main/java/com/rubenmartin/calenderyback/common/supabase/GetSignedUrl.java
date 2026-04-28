package com.rubenmartin.calenderyback.common.supabase;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Getter
public class GetSignedUrl {

    @Value("${SUPABASE_URL}")
    private String supabaseUrl;

    @Value("${SERVICE_KEY}")
    private String serviceKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSignedUrl(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + serviceKey);
        headers.set("apikey", serviceKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("expiresIn", 3600);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String partialUrl = (String) response.getBody().get("url");

                return supabaseUrl + "/storage/v1" + partialUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createUploadSignedUrl(String bucketName, String path) {
        String url = String.format("%s/storage/v1/object/upload/sign/%s/%s", supabaseUrl, bucketName, path);
        return getSignedUrl(url);
    }

    public String createStorageSignedUrl(String bucketName, String path) {
        String url = String.format("%s/storage/v1/object/sign/%s/%s", supabaseUrl, bucketName, path);
        return getSignedUrl(url);
    }

}
