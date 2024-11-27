package lk.ijse.culinary.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypt {
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
