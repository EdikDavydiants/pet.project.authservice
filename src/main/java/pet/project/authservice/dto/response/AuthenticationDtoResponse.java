package pet.project.authservice.dto.response;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDtoResponse (

        @NotNull
        String token,

        @NotNull
        String tokenType,

        @NotNull
        Long expiresIn
) {
        public static AuthenticationDtoResponseBuilder builder() {
                return new AuthenticationDtoResponseBuilder();
        }

        public static class AuthenticationDtoResponseBuilder {
                private @NotNull String token;
                private @NotNull String tokenType;
                private @NotNull Long expiresIn;

                AuthenticationDtoResponseBuilder() {
                }

                public AuthenticationDtoResponseBuilder token(@NotNull String token) {
                        this.token = token;
                        return this;
                }

                public AuthenticationDtoResponseBuilder tokenType(@NotNull String tokenType) {
                        this.tokenType = tokenType;
                        return this;
                }

                public AuthenticationDtoResponseBuilder expiresIn(@NotNull Long expiresIn) {
                        this.expiresIn = expiresIn;
                        return this;
                }

                public AuthenticationDtoResponse build() {
                        return new AuthenticationDtoResponse(this.token, this.tokenType, this.expiresIn);
                }

                public String toString() {
                        return "AuthenticationDtoResponse.AuthenticationDtoResponseBuilder(token=" + this.token + ", tokenType=" + this.tokenType + ", expiresIn=" + this.expiresIn + ")";
                }
        }
}
