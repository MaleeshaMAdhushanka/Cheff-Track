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
import lk.ijse.culinary.bo.custom.impl.AdminBOImpl;

import java.io.IOException;

public class AdminDashboardMainFormController {

    @FXML
    private MFXButton btnCourse;

    @FXML
    private MFXButton btnDashboard;


    @FXML
    private MFXButton btnSetting;

    @FXML
    private MFXButton btnStudent;

    @FXML
    private Circle cirAdminImage;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private Label idAdminName;

    @FXML
    private Pane imgAndNameHolderPane;

    public void initialize() throws IOException {
        setAdminNameAndImage(true);
        loadAdminDashBoardForm();

    }

    private void setAdminNameAndImage(boolean flag) {
        imgAndNameHolderPane.setVisible(flag);
        Platform.runLater(() -> {
            idAdminName.setText(AdminBOImpl.loggedAdmin.getUsername());
            Image image = new Image(AdminBOImpl.loggedAdmin.getImgUrl());
            cirAdminImage.setFill(new ImagePattern(image));

        });
    }



    private void loadAdminDashBoardForm() throws IOException {
        setButtonColors(Pages.DASHBOARD);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
    }

    @FXML
    void btnCourcesOnAction(ActionEvent event) throws IOException {
        setButtonColors(Pages.COURSE);
        setAdminNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/courseForm.fxml"));
        Pane bookPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(bookPane);
    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {
        setButtonColors(Pages.PAYMENTS);
        setAdminNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/coursePaymentForm.fxml"));
        Pane borrowBookPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(borrowBookPane);
    }



    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        setButtonColors(Pages.DASHBOARD);
        setAdminNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminDashBoardForm.fxml"));
        Pane dashboardPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(dashboardPane);
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
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        setButtonColors(Pages.SETTINGS);
        setAdminNameAndImage(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminSettingsForm.fxml"));
        Pane settingPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(settingPane);
    }


    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        setButtonColors(Pages.STUDENT);
        setAdminNameAndImage(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/studentForm.fxml"));
        Pane userPane = (Pane) fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(userPane);
    }

    public enum Pages{
        DASHBOARD,COURSE, PAYMENTS, SETTINGS, STUDENT
    }

    private void setButtonColors(Pages page){
        btnDashboard.getStyleClass().remove("mfx-button-Dashboard-active");
        btnCourse.getStyleClass().remove("mfx-button-Book-active");

        btnSetting.getStyleClass().remove("mfx-button-Settings-active");
        btnStudent.getStyleClass().remove("mfx-button-User-active");

        switch (page){
            case DASHBOARD:
                btnDashboard.getStyleClass().add("mfx-button-Dashboard-active");
                break;
            case COURSE:
                btnCourse.getStyleClass().add("mfx-button-Book-active");
                break;
            case SETTINGS:
                btnSetting.getStyleClass().add("mfx-button-Settings-active");
                break;
            case STUDENT:
                btnStudent.getStyleClass().add("mfx-button-User-active");
                break;
        }
    }




}

