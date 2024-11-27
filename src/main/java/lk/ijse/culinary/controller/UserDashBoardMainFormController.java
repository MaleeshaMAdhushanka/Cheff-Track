package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.custom.impl.UserBOImpl;

import java.io.IOException;

public class UserDashBoardMainFormController {

    @FXML
    private MFXButton btnPayment;

    @FXML
    private MFXButton btnDashboard;

    @FXML
    private MFXButton btnRegisterStudent;

    @FXML
    private MFXButton btnSetting;


    @FXML
    private Circle cirUserImage;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private Label idUserName;

    @FXML
    private Pane imgAndNameHolderPane;
    public void initialize() throws IOException {
        setUserNameAndImage(true);
        loadUserDashBoardForm();

    }
    private void loadUserDashBoardForm() throws IOException {
        setButtonColors(Pages.DASHBOARD);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userDashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }
    private void setUserNameAndImage(boolean flag) {
        imgAndNameHolderPane.setVisible(flag);
        Platform.runLater(() -> {
            idUserName.setText(UserBOImpl.loggedUser.getName());
            Image image = new Image(UserBOImpl.loggedUser.getImgUrl());
            cirUserImage.setFill(new ImagePattern(image));
        });
    }
    @FXML
    void btnPaymentsOnAction(ActionEvent event){
        setButtonColors(Pages.PAYMENTS);
        setUserNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/coursePaymentForm.fxml"));
        Pane coursesPane = null;
        try {
            coursesPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(coursesPane);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event){
        setButtonColors(Pages.DASHBOARD);
        setUserNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userDashboardForm.fxml"));
        Pane settingPane = null;
        try {
            settingPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

        //Close the Current Window
        Stage dashboard= (Stage) holderPane.getScene().getWindow();
        dashboard.close();
    }

    @FXML
    void btnRegisterStudentOnAction(ActionEvent event) {
        setButtonColors(Pages.REGISTER);
        setUserNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/studentForm.fxml"));
        Pane registerPane = null;
        try {
            registerPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(registerPane);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) {
        setButtonColors(Pages.SETTINGS);
        setUserNameAndImage(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userSettingsForm.fxml"));
        Pane settingPane = null;
        try {
            settingPane = (Pane) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);
    }

    public enum Pages{
        DASHBOARD,PAYMENTS, REGISTER, SETTINGS
    }

    private void setButtonColors(Pages page){
        btnDashboard.getStyleClass().remove("mfx-button-Dashboard-active");
        btnRegisterStudent.getStyleClass().remove("mfx-button-BookHistory-active");
        btnPayment.getStyleClass().remove("mfx-button-BorrowBooks-active");
        btnSetting.getStyleClass().remove("mfx-button-Settings-active");


        switch (page){
            case DASHBOARD:
                btnDashboard.getStyleClass().add("mfx-button-Dashboard-active");
                break;
            case PAYMENTS:
                btnPayment.getStyleClass().add("mfx-button-BorrowBooks-active");
                break;
            case REGISTER:
                btnRegisterStudent.getStyleClass().add("mfx-button-BookHistory-active");
                break;
            case SETTINGS:
                btnSetting.getStyleClass().add("mfx-button-Settings-active");
                break;

        }
    }


}
