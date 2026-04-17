package com.rubenmartin.calenderyback.user.application.command.registrationComplete;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import com.rubenmartin.calenderyback.vertificationToken.domain.port.VerificationTokenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private VerificationTokenPort verificationTokenPort;

    @Autowired
    private MessageSource messages;

    @Value("${BREVO_API_KEY}")
    private String apiKey;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenPort.createVerificationToken(user, token);
        String createdToken = "https://calenderyback.onrender.com/?token=" + token;

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.brevo.com/v3/smtp/email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        Map<String, Object> body = new HashMap<>();

        body.put("sender", Map.of("name", "Mi App Spring", "email", "martinandraderuben@gmail.com"));

        body.put("to", List.of(Map.of("email", user.getEmail(), "name", user.getNombre())));

        body.put("subject", "Welcome to mi app!");
        body.put("htmlContent", createdToken);

        try {
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        } catch (Exception e) {
            System.err.println("Error al enviar el mail: " + e.getMessage());
        }
    }
}
