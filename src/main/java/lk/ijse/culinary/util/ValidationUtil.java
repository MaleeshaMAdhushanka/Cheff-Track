package lk.ijse.culinary.util;

import javafx.scene.control.TextField;

public class ValidationUtil {
    public static boolean isNotEmpty(String textField) {
        return !textField.trim().isEmpty();
    }

    public static boolean isNumeric(TextField textField) {
        try {
            Double.parseDouble(textField.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String textField) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return textField.trim().matches(emailPattern);
    }
}