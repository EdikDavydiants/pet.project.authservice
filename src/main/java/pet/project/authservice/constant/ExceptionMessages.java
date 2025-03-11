package pet.project.authservice.constant;

public class ExceptionMessages {

    public static final String UNKNOWN_ERROR = "Something went wrong!";
    public static final String INVALID_PASSWORD = "Password is invalid!";
    public static final String INVALID_PASSWORD_DETAILS = "You have entered an incorrect password.";
    public static final String USER_NOT_FOUND = "User is not found!";
    public static final String USER_NOT_FOUND_DETAILS = "Username with such name does not exist.";

    public static final long TOKEN_LIFE_PERIOD_MS = 3600 * 24 * 1000;
}
