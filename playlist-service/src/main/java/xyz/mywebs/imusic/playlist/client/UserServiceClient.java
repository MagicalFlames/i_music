package xyz.mywebs.imusic.playlist.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class UserServiceClient {
    private final RestTemplate restTemplate;

    @Value("${service.user-url}")
    private String userServiceUrl;

    public UserServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    @SuppressWarnings("unchecked")
    public boolean validateUser(String username, String password) {
        try {
            String url = userServiceUrl + "/api/user/validate?username=" + username + "&password=" + password;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && (Boolean) response.get("success")) {
                Map<String, Object> message = (Map<String, Object>) response.get("message");
                return (Boolean) message.get("valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
