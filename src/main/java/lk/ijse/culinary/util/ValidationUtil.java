package lk.ijse.culinary.util;

public class ValidationUtil {
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }

    public static boolean isNumeric(String value) {
        String numericRegex = "\\d+";
        return value.matches(numericRegex);
    }
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }
    public static boolean isDecimal(String value) {
        String decimalRegex = "/(\\-?\\d*\\.?\\d+)/";
        return value.matches(decimalRegex);
    }

    public static boolean isValidName(String name) {
        String nameRegex = "^[a-zA-Z\\s]+$";
        return name.matches(nameRegex);
    }

    public static boolean isValidContact(String contact) {
        String contactRegex = "^[0-9]{10}$";
        return contact.matches(contactRegex);
    }

    public static boolean isValidAddress(String address) {
        return isNotEmpty(address);
    }
}