package pet.project.authservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pet.project.authservice.dto.request.RegistrationDtoRequest;
import pet.project.authservice.dto.response.RegistrationDtoResponse;

@Service
public class RegistrationService {

    private final RestTemplate restTemplate;

    @Value("${service.user.url}")
    private String userServiceUrl;

    public RegistrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RegistrationDtoResponse registerNewUser(RegistrationDtoRequest request) {

        valid(request);
        return restTemplate.postForObject(userServiceUrl, request, RegistrationDtoResponse.class);
    }

    public void valid(RegistrationDtoRequest request) {

    }
}
