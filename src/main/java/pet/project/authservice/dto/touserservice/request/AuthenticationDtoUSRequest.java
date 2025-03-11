package pet.project.authservice.dto.touserservice.request;

public record AuthenticationDtoUSRequest(

        String username
) {
    public static AuthenticationDtoUSRequestBuilder builder() {
        return new AuthenticationDtoUSRequestBuilder();
    }

    public static class AuthenticationDtoUSRequestBuilder {
        private String username;

        AuthenticationDtoUSRequestBuilder() {
        }

        public AuthenticationDtoUSRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationDtoUSRequest build() {
            return new AuthenticationDtoUSRequest(this.username);
        }

        public String toString() {
            return "AuthenticationDtoUSRequest.AuthenticationDtoUSRequestBuilder(username=" + this.username + ")";
        }
    }
}
