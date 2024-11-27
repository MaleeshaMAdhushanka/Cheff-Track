package lk.ijse.culinary.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerifier {
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
