package pet.project.authservice.dto;

import lombok.Builder;

@Builder
public record GeneralErrorResponse(

        int status,

        String type,

        String message,

        String details
) {
}
