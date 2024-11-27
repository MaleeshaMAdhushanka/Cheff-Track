package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.AdminBO;
import lk.ijse.culinary.bo.custom.impl.AdminBOImpl;
import lk.ijse.culinary.dto.AdminDto;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AdminSettingsFormController {
    

    @FXML
    private MFXPasswordField txtPassword;

    @FXML
    private MFXTextField txtUsername;


    @FXML
    private Circle circleImg;

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);



    public void initialize(){
        loadDetails();
    }

    private void loadDetails() {
        txtUsername.setText(AdminBOImpl.loggedAdmin.getUsername());
        txtPassword.setText(AdminBOImpl.loggedAdmin.getPassword());
        Platform.runLater(() -> {
            Image image = new Image(AdminBOImpl.loggedAdmin.getImgUrl());
            circleImg.setFill(new ImagePattern(image));
        });
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        boolean isDeleted = adminBO.deleteAdmin(AdminBOImpl.loggedAdmin.getUsername());
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Successfully").show();
            loadLoginForm();
        } else {
            new Alert(Alert.AlertType.ERROR, "Delete Failed").show();
        }
    }

    private void loadLoginForm() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

        //Close the Current Window
        Stage currentStage = (Stage) txtUsername.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isLoginValidated = validateRegister();

        if (!isLoginValidated) {
            return;
        }

        String imgUrl = imageSave();

        AdminDto adminDto = new AdminDto(txtUsername.getText(), txtPassword.getText(),imgUrl);
        boolean isSaved = adminBO.updateAdmin(adminDto);
        if (isSaved) {
            updateAdminDetails(adminDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    private void updateAdminDetails(AdminDto adminDto) {
        AdminBOImpl.loggedAdmin.setUsername(adminDto.getUsername());
        AdminBOImpl.loggedAdmin.setPassword(adminDto.getPassword());
        AdminBOImpl.loggedAdmin.setImgUrl(adminDto.getImgUrl());
    }

    private String imageSave() {
        try {
            ImagePattern imagePattern = (ImagePattern) circleImg.getFill();
            Image userImage = imagePattern.getImage();
            URI uri = new URI(userImage.getUrl());

            File file = new File(uri);
            String sourceLocation = file.getAbsolutePath();

            // Get the users home directory in a platform independent way
            String userHomeDir = System.getProperty("user.home");
            Path directoryPath = Paths.get(userHomeDir, "Desktop", "admins");

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
        boolean isUserNameValid =txtUsername.getText().matches("^[a-zA-Z0-9._]{3,}$");
        if (!isUserNameValid) {
            txtUsername.requestFocus();
            txtUsername.getStyleClass().add("mfx-text-field-details-error");
            return false;
        }

        txtUsername.getStyleClass().remove("mfx-text-field-details-error");

        boolean isPasswordValid = txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$");

        if (!isPasswordValid) {
            txtPassword.requestFocus();
            txtPassword.getStyleClass().add("mfx-text-field-details-error");
            return false;
        }

        txtPassword.getStyleClass().remove("mfx-text-field-details-error");
        return true;
    }

    @FXML
    void circleImgOnAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            Image selectedImage = new Image(file.toURI().toString());
            circleImg.setFill(new ImagePattern(selectedImage));
        }
    }

    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
    }

}

