package com.rubenmartin.calenderyback.user.application.command.resendToken;

import com.rubenmartin.calenderyback.common.mediator.RequestHandler;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.vertificationToken.domain.entity.VerificationToken;
import com.rubenmartin.calenderyback.vertificationToken.domain.exception.TokenNotFoundException;
import com.rubenmartin.calenderyback.vertificationToken.domain.port.VerificationTokenPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ResendTokenHandler implements RequestHandler<ResendTokenRequest, Void> {

    private final VerificationTokenPort verificationTokenPort;

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    public ResendTokenHandler(VerificationTokenPort verificationTokenPort) {
        this.verificationTokenPort = verificationTokenPort;
    }

    @Override
    public Void handle(ResendTokenRequest request) {
        Long tokenId = request.getIdUsuario();

        Optional<VerificationToken> verificationToken = verificationTokenPort.findByUserId(tokenId);

        if (verificationToken.isEmpty())
            throw new TokenNotFoundException();

        String token = verificationToken.get().getToken();

        VerificationToken newToken = verificationTokenPort.generateNewVerificationToken(token);

        User user = newToken.getUser();
        resendVerificationToken(user, newToken);

        return null;
    }

    @Override
    public Class<ResendTokenRequest> getRequestType() {
        return ResendTokenRequest.class;
    }


    private void resendVerificationToken(User user, VerificationToken verificationToken) {
        String createdToken = "https://calenderyback.onrender.com/?token=" + verificationToken.getToken();

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
