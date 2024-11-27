package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.UserBO;
import lk.ijse.culinary.dto.UserDto;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserLoginFormController {

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Label lblError;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        boolean isLoginValidated = validateLogin();

        if (!isLoginValidated) {
            return;
        }

        UserDto userDto = new UserDto(txtEmail.getText(), txtPassword.getText());

        boolean isUserExist= userBO.isUserExist(userDto);

        if (!isUserExist){
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
            //Highlight Fields
            txtEmail.getStyleClass().add("mfx-text-field-error");
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return;
        }

        clearFields();

        openDashboard();
    }

    private void clearFields() {
        txtEmail.clear();
        txtPassword.clear();
    }

    private void openDashboard() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/userDashBoardMainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();

        Stage loginStage = (Stage) loginPane.getScene().getWindow();
        loginStage.close();
    }

    private boolean validateLogin() {
        boolean isEmailValid = Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", txtEmail.getText());
        if (!isEmailValid) {
            txtEmail.requestFocus();
            txtEmail.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtEmail.getStyleClass().remove("mfx-text-field-error");

        boolean isPasswordValid = Pattern.matches("^[a-zA-Z0-9@#]{3,}$", txtPassword.getText());
        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-error");

        return true;
    }

    @FXML
    void btnRegister(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userImageForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(registerPane);
    }

    @FXML
    void btnAdminLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane adminLoginPane = fxmlLoader.load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(adminLoginPane);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        btnLogin(event);
    }
}