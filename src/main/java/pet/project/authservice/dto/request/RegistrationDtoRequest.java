package pet.project.authservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegistrationDtoRequest(

        @NotNull
        String username,

        @NotNull
        String email,

        @NotNull
        String password,

        String name,

        String bio
) {
}
