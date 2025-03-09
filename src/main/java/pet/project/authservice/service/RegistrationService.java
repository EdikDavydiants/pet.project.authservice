package pet.project.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pet.project.authservice.dto.request.RegistrationDtoRequest;
import pet.project.authservice.dto.response.RegistrationDtoResponse;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RestTemplate restTemplate;

    @Value("${service.user.url}")
    private String userServiceUrl;

    public RegistrationDtoResponse registerNewUser(RegistrationDtoRequest request) {

        valid(request);
        return restTemplate.postForObject(userServiceUrl, request, RegistrationDtoResponse.class);
    }

    public void valid(RegistrationDtoRequest request) {

    }
}
