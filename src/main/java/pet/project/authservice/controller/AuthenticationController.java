package pet.project.authservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.project.authservice.dto.request.AuthenticationDtoRequest;
import pet.project.authservice.dto.response.AuthenticationDtoResponse;
import pet.project.authservice.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthenticationDtoResponse authenticateUser(
            @Valid @RequestBody AuthenticationDtoRequest request) {

        return authenticationService.authenticateUser(request);
    }
}
