package pet.project.authservice.dto.touserservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationDtoUSRequest(

        @NotNull
        String username,

        @NotNull
        String email,

        @NotNull
        @Size(min = 60, max = 60)
        String passwordHash,

        String name,

        String bio
) {
        public static RegistrationDtoUSRequestBuilder builder() {
                return new RegistrationDtoUSRequestBuilder();
        }

        public static class RegistrationDtoUSRequestBuilder {
                private @NotNull String username;
                private @NotNull String email;
                private @NotNull @Size(min = 60, max = 60) String passwordHash;
                private String name;
                private String bio;

                RegistrationDtoUSRequestBuilder() {
                }

                public RegistrationDtoUSRequestBuilder username(@NotNull String username) {
                        this.username = username;
                        return this;
                }

                public RegistrationDtoUSRequestBuilder email(@NotNull String email) {
                        this.email = email;
                        return this;
                }

                public RegistrationDtoUSRequestBuilder passwordHash(@NotNull @Size(min = 60, max = 60) String passwordHash) {
                        this.passwordHash = passwordHash;
                        return this;
                }

                public RegistrationDtoUSRequestBuilder name(String name) {
                        this.name = name;
                        return this;
                }

                public RegistrationDtoUSRequestBuilder bio(String bio) {
                        this.bio = bio;
                        return this;
                }

                public RegistrationDtoUSRequest build() {
                        return new RegistrationDtoUSRequest(this.username, this.email, this.passwordHash, this.name, this.bio);
                }

                public String toString() {
                        return "RegistrationDtoUSRequest.RegistrationDtoUSRequestBuilder(username=" + this.username + ", email=" + this.email + ", passwordHash=" + this.passwordHash + ", name=" + this.name + ", bio=" + this.bio + ")";
                }
        }
}
