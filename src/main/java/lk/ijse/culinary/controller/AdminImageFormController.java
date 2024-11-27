package lk.ijse.culinary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lk.ijse.culinary.bo.custom.impl.AdminBOImpl;

import java.io.File;
import java.io.IOException;

public class AdminImageFormController {


    @FXML
    private Circle circleImg;

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane registerPane;


    public void initialize() {
        loadDefaultImage();
    }

    private void loadDefaultImage() {
        Image image = new Image("/assets/images/addUserImage.png");
        circleImg.setFill(new ImagePattern(image));
    }


    @FXML
    void btnLogin(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
    }

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {
        //Set Admin Image Temporarily
        AdminBOImpl.circleImg = this.circleImg;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminRegisterForm.fxml"));
        Pane loginPane = (Pane) fxmlLoader.load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(loginPane);
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

