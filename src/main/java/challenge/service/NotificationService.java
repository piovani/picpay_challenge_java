package challenge.service;

import challenge.domain.user.User;
import challenge.dtos.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationRequest notificationRequest = new NotificationRequest(email, message);

        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new Exception("Falha ao notificar usuario");
        }
    }
}
