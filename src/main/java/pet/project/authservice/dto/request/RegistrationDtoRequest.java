package pet.project.authservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistrationDtoRequest(

        @NotNull
        String username,

        @NotNull
        String email,

        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password must contain latin letters and digits!")
        @Size(min = 8, message = "Password is too small")
        @Size(max = 25, message = "Password is too long")
        String password,

        String name,

        String bio
) {
}
