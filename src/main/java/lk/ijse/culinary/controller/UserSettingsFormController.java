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

public class UserSettingsFormController {



    @FXML
    private Circle circleImg;

    @FXML
    private MFXTextField txtAddress;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXPasswordField txtPassword;


    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);


    public void initialize(){
        loadDetails();
    }

    private void loadDetails() {

        txtEmail.setText(UserBOImpl.loggedUser.getEmail());
        txtName.setText(UserBOImpl.loggedUser.getName());
        txtAddress.setText(UserBOImpl.loggedUser.getAddress());
        txtPassword.setText(UserBOImpl.loggedUser.getPassword());

        Platform.runLater(() -> {
            Image image = new Image(UserBOImpl.loggedUser.getImgUrl());
            circleImg.setFill(new ImagePattern(image));
        });

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {

        boolean isDeleted = userBO.deleteUser(UserBOImpl.loggedUser.getEmail());

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
        Stage currentStage = (Stage) txtEmail.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {


        boolean isValidated = validateRegister();

        if (!isValidated) {
            return;
        }

        String imgUrl = imageSave();

        UserDto userDto = new UserDto(txtEmail.getText(), txtName.getText(), txtAddress.getText(), txtPassword.getText(), imgUrl);
        boolean isSaved = userBO.updateUserAndImg(userDto);

        if (isSaved) {
            updateUserDetails(userDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }

    }

    private void updateUserDetails(UserDto userDto) {
        UserBOImpl.loggedUser.setEmail(userDto.getEmail());
        UserBOImpl.loggedUser.setName(userDto.getName());
        UserBOImpl.loggedUser.setAddress(userDto.getAddress());
        UserBOImpl.loggedUser.setPassword(userDto.getPassword());
        UserBOImpl.loggedUser.setImgUrl(userDto.getImgUrl());
    }

    private String imageSave() {
        try {
            ImagePattern imagePattern = (ImagePattern) circleImg.getFill();
            Image userImage = imagePattern.getImage();
            URI uri = new URI(userImage.getUrl());

            if (!"file".equals(uri.getScheme())) {
                throw new IllegalArgumentException("URI is not hierarchical");
            }

            File file = new File(uri);
            String sourceLocation = file.getAbsolutePath();

            // Get the users home directory in a platform independent way
            String userHomeDir = System.getProperty("user.home");
            Path directoryPath = Paths.get(userHomeDir, "Desktop", "users");

            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            if (!sourceLocation.equals("assets/images/addUserImage.png")) {
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

        boolean isValid = true;

        if (!txtName.getText().matches("^[\\p{L} '-]+$")) {
            txtName.getStyleClass().add("text-field-error");
            txtName.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtName.getStyleClass().remove("text-field-error");
            txtName.getStyleClass().add("text-field-success");
        }

        if (!txtAddress.getText().matches("^[a-zA-Z0-9,._#()/:;]+$")) {
            txtAddress.getStyleClass().add("text-field-error");
            txtAddress.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtAddress.getStyleClass().remove("text-field-error");
            txtAddress.getStyleClass().add("text-field-success");
        }

        if (!txtEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            txtEmail.getStyleClass().add("text-field-error");
            txtEmail.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtEmail.getStyleClass().remove("text-field-error");
            txtEmail.getStyleClass().add("text-field-success");
        }

        if (!txtPassword.getText().matches("^[a-zA-Z0-9@#]{3,}$")) {
            txtPassword.getStyleClass().add("text-field-error");
            txtPassword.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtPassword.getStyleClass().remove("text-field-error");
            txtPassword.getStyleClass().add("text-field-success");
        }

        return isValid;
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
                new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", ".gif", ".jpeg")
        );
    }



}