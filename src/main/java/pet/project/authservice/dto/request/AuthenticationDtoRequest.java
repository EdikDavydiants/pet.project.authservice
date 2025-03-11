package pet.project.authservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDtoRequest(

        @NotNull
        String username,

        @NotNull
        String password
) {
}
