package pet.project.authservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.project.authservice.dto.request.RegistrationDtoRequest;
import pet.project.authservice.dto.response.RegistrationDtoResponse;
import pet.project.authservice.dto.touserservice.request.RegistrationDtoUSRequest;
import pet.project.authservice.service.RegistrationService;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public RegistrationDtoResponse registerNewUser(@Valid @RequestBody RegistrationDtoRequest request) {
        return registrationService.registerNewUser(request);
    }
}
