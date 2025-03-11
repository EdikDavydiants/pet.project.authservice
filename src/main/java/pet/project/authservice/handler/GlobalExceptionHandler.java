package pet.project.authservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pet.project.authservice.dto.GeneralErrorResponse;
import pet.project.authservice.exception.InvalidPasswordException;
import pet.project.authservice.exception.UnknownException;
import pet.project.authservice.exception.UserNotFoundException;

import static pet.project.authservice.constant.ExceptionMessages.INVALID_PASSWORD;
import static pet.project.authservice.constant.ExceptionMessages.INVALID_PASSWORD_DETAILS;
import static pet.project.authservice.constant.ExceptionMessages.USER_NOT_FOUND_DETAILS;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Unknown error")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<GeneralErrorResponse> handleUnknownException(UnknownException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Unknown error")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .type("UserNotFoundError")
                        .message(exception.getMessage())
                        .details(USER_NOT_FOUND_DETAILS)
                        .build());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<GeneralErrorResponse> handleInvalidPasswordException(InvalidPasswordException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .type("InvalidPasswordError")
                        .message(exception.getMessage())
                        .details(INVALID_PASSWORD_DETAILS)
                        .build());
    }
}
