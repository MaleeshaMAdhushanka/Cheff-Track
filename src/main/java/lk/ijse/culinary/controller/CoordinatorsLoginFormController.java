package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class CoordinatorsLoginFormController {

        @FXML
        private Label lblError;

        @FXML
        private AnchorPane loginPane;

        @FXML
        private MFXTextField txtEmail;

        @FXML
        private MFXPasswordField txtPassword;

        @FXML
        void btnAdminLogin(MouseEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
            Pane adminLoginPane = (Pane) fxmlLoader.load();
            loginPane.getChildren().clear();
            loginPane.getChildren().add(adminLoginPane);


        }

        @FXML
        void btnLogin(ActionEvent event) throws IOException {
            openDashboard();

        }

    private void openDashboard() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/CoordinatorsDashboardMain.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();

        //Close the Current Window
        Stage loginStage = (Stage) loginPane.getScene().getWindow();
        loginStage.close();
    }

    @FXML
        void btnRegister(MouseEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CoordinatorImageFrom.fxml"));
            Pane adminLoginPane = (Pane) fxmlLoader.load();
            loginPane.getChildren().clear();
            loginPane.getChildren().add(adminLoginPane);

        }

        @FXML
        void txtPasswordOnAction(ActionEvent event) throws IOException {
                btnLogin(event);
        }

    }



