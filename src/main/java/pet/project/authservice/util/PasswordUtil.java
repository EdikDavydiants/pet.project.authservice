package pet.project.authservice.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean validPassword(String password, String passwordHash) {
        return passwordHash.equals(hashPassword(password));
    }
}
