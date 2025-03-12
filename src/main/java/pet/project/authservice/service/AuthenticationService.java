package pet.project.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pet.project.authservice.dto.request.AuthenticationDtoRequest;
import pet.project.authservice.dto.response.AuthenticationDtoResponse;
import pet.project.authservice.dto.touserservice.request.AuthenticationDtoUSRequest;
import pet.project.authservice.dto.touserservice.response.AuthenticationDtoUSResponse;
import pet.project.authservice.exception.InvalidPasswordException;
import pet.project.authservice.exception.UnknownException;
import pet.project.authservice.exception.UserNotFoundException;

import java.util.Date;

import static pet.project.authservice.constant.ExceptionMessages.INVALID_PASSWORD;
import static pet.project.authservice.constant.ExceptionMessages.TOKEN_LIFE_PERIOD_MS;
import static pet.project.authservice.constant.ExceptionMessages.UNKNOWN_ERROR;
import static pet.project.authservice.constant.ExceptionMessages.USER_NOT_FOUND;
import static pet.project.authservice.util.PasswordUtil.validPassword;

@Service
public class AuthenticationService {

    @Value("${service.user.url}")
    private String userServiceUrl;
    private final RestTemplate restTemplate;

    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthenticationDtoResponse authenticateUser(AuthenticationDtoRequest request) {

        var usRequest = AuthenticationDtoUSRequest.builder()
                .username(request.username())
                .build();
        HttpEntity<AuthenticationDtoUSRequest> usEntityRequest = new HttpEntity<>(usRequest);

        ResponseEntity<AuthenticationDtoUSResponse> usResponseEntity =
                restTemplate.exchange(userServiceUrl + "/login", HttpMethod.POST, usEntityRequest, AuthenticationDtoUSResponse.class);

        if (usResponseEntity.getStatusCode().is2xxSuccessful()) {
            var usResponse = usResponseEntity.getBody();
            String token = Jwts.builder()
                    .claims()
                        .id(usResponse.id().toString())
                        .subject(request.username())
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + TOKEN_LIFE_PERIOD_MS))
                    .and()
                    .signWith(SignatureAlgorithm.HS256, "это секретный ключ, который хранится в коде")
                    .compact();
            if (!validPassword(request.password(), usResponse.passwordHash())) {
                throw new InvalidPasswordException(INVALID_PASSWORD);
            }
            return AuthenticationDtoResponse.builder()
                    .token(token)
                    .tokenType("Bearer")
                    .expiresIn(TOKEN_LIFE_PERIOD_MS / 1000)
                    .build();
        }
        else if (usResponseEntity.getStatusCode().is4xxClientError()){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        else {
            throw new UnknownException(UNKNOWN_ERROR);
        }
    }
}
