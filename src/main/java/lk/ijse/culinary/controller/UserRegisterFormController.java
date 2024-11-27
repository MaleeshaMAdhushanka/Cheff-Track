package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.UserBO;
import lk.ijse.culinary.bo.custom.impl.UserBOImpl;
import lk.ijse.culinary.dto.UserDto;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UserRegisterFormController {

    @FXML
    private AnchorPane registerPane;

    @FXML
    private MFXPasswordField txtConfirmPassword;

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;


    @FXML
    private MFXTextField txtAddress;

    @FXML
    private Label lblError;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);



    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void btnRegister(ActionEvent event) throws IOException {

        boolean isLoginValidated = validateRegister();

        if (!isLoginValidated) {
            return;
        }

        String imgUrl = imageSave();

        UserDto userDto = new UserDto(txtEmail.getText(),txtName.getText(),txtAddress.getText(),txtPassword.getText(),imgUrl);
        boolean isSaved = userBO.saveUser(userDto);

        if (isSaved) {
            clearFields();
            loadLoginPane();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Already Exists ").show();
        }
    }

    private void loadLoginPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    private String imageSave() {
        try {
            ImagePattern imagePattern = (ImagePattern) UserBOImpl.circleImg.getFill();
            Image userImage = imagePattern.getImage();
            URI uri = new URI(userImage.getUrl());

            File file = new File(uri);
            String sourceLocation = file.getAbsolutePath();

            // Get the users home directory in a platform independent way
            String userHomeDir = System.getProperty("user.home");
            Path directoryPath = Paths.get(userHomeDir, "Desktop", "users");

            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            if (!(sourceLocation.equals("assets/images/addUserImage.png"))) {
                Path sourcePath = file.toPath();
                Path destinationPath = Paths.get(directoryPath.toString(), file.getName());
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                return "file:" + destinationPath;
            }

            return "assets/images/addUserImage.png";

        } catch (URISyntaxException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "Check The File Path").show();
            throw new RuntimeException(e);
        }
    }

    private boolean validateRegister() {

        boolean isNameValid = txtName.getText().matches("^[\\p{L} '-]+$");
        if (!isNameValid) {
            txtName.requestFocus();
            txtName.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtName.getStyleClass().remove("mfx-text-field-error");


        boolean isAddressValid = txtAddress.getText().matches("^[a-zA-Z0-9,._#()/:;]+$");

        if (!isAddressValid) {
            txtAddress.requestFocus();
            txtAddress.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAddress.getStyleClass().remove("mfx-text-field-error");


        boolean isEmailValid = txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (!isEmailValid) {
            txtEmail.requestFocus();
            txtEmail.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtEmail.getStyleClass().remove("mfx-text-field-error");

        boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$");

        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-error");

        boolean isConfirmPasswordValid = txtConfirmPassword.getText().matches("^[a-zA-Z0-9]{3,}$");

        if (!isConfirmPasswordValid) {
            txtConfirmPassword.requestFocus();
            txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtConfirmPassword.getStyleClass().remove("mfx-text-field-error");


        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            lblError.setVisible(true);
            txtPassword.getStyleClass().add("mfx-text-field-error");
            txtConfirmPassword.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        lblError.setVisible(false);
        txtPassword.getStyleClass().remove("mfx-text-field-error");
        txtConfirmPassword.getStyleClass().remove("mfx-text-field-error");

        return true;
    }

}

