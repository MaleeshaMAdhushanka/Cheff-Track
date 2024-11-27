package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.AdminBO;
import lk.ijse.culinary.dto.AdminDto;

import java.io.IOException;

public class AdminLoginFormController {

    @FXML
    private AnchorPane adminLoginPane;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void btnUserLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
        Pane userLoginPane = (Pane) fxmlLoader.load();
        adminLoginPane.getChildren().clear();
        adminLoginPane.getChildren().add(userLoginPane);
    }

    @FXML
    void btnLogin(ActionEvent event) throws IOException {


        boolean isLoginValidated = validateLogin();

        if (!isLoginValidated) {
            return;
        }

        AdminDto adminDto = new AdminDto(txtUsername.getText(), txtPassword.getText());

        boolean isAdminExist = adminBO.isAdminExist(adminDto);

        if (!isAdminExist){
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
            //Highlight Fields
            txtUsername.getStyleClass().add("mfx-text-field-error");
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return;
        }

        //Clear Fields
        clearFields();
        //Open the Dashboard
        openDashboard();
    }

    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
    }

    private void openDashboard() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminDashBoardMainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();

        //Close the Current Window
        Stage loginStage = (Stage) adminLoginPane.getScene().getWindow();
        loginStage.close();
    }

    private boolean validateLogin() {

        boolean isUsernameValid = txtUsername.getText().matches("^[a-zA-Z0-9._]{3,}$");

        if (!isUsernameValid) {
            txtUsername.requestFocus();
            txtUsername.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtUsername.getStyleClass().remove("mfx-text-field-error");

        boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$");

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminImageForm.fxml"));
        Pane adminRegister = (Pane) fxmlLoader.load();
        adminLoginPane.getChildren().clear();
        adminLoginPane.getChildren().add(adminRegister);

    }


    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        btnLogin(event);
    }



}

