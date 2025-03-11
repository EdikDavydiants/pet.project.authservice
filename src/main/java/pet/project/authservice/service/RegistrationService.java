package pet.project.authservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pet.project.authservice.dto.request.RegistrationDtoRequest;
import pet.project.authservice.dto.response.RegistrationDtoResponse;
import pet.project.authservice.dto.touserservice.request.RegistrationDtoUSRequest;
import pet.project.authservice.exception.UnknownException;
import pet.project.authservice.mapper.RegistrationDtoMapper;

import static pet.project.authservice.constant.ExceptionMessages.UNKNOWN_ERROR;
import static pet.project.authservice.util.PasswordUtil.hashPassword;

@Service
public class RegistrationService {

    private final RestTemplate restTemplate;

    @Value("${service.user.url}")
    private String userServiceUrl;

    public RegistrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RegistrationDtoResponse registerNewUser(RegistrationDtoRequest request) {

        var usRequest = RegistrationDtoUSRequest.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(hashPassword(request.password()))
                .name(request.name())
                .bio(request.bio())
                .build();

//        var usRequest = RegistrationDtoMapper.INSTANCE.registrationDtoRequestToUSRequest(request);

        HttpEntity<RegistrationDtoUSRequest> usRequestEntity = new HttpEntity<>(usRequest);

        ResponseEntity<RegistrationDtoResponse> usResponseEntity =
                restTemplate.exchange(userServiceUrl + "/register", HttpMethod.POST, usRequestEntity, RegistrationDtoResponse.class);

        if (usResponseEntity.getStatusCode().is2xxSuccessful()) {
            return usResponseEntity.getBody();
        } else {
            throw new UnknownException(UNKNOWN_ERROR);
        }
    }
}
