package util;

import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class PasswordManager {

    public String hashPassword(String clear) {
        return BCrypt.hashpw(clear, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String clear, String hashed) {
        try {
            return BCrypt.checkpw(clear, hashed);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
