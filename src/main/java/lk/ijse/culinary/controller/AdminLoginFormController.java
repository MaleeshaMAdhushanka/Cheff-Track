package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminLoginFormController {

        @FXML
        private AnchorPane adminLoginPane;

        @FXML
        private MFXPasswordField txtPassword;

        @FXML
        private MFXTextField txtUsername;

        @FXML
        void btnLogin(ActionEvent event) throws IOException {

                openDashboard();

        }

        private void openDashboard() throws IOException {
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminDashBoardMainFrom.fxml"));
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

        @FXML
        void btnRegister(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminImageForm.fxml"));
                Pane userLoginPane = (Pane) fxmlLoader.load();
                adminLoginPane.getChildren().clear();
                adminLoginPane.getChildren().add(userLoginPane);


        }

        @FXML
        void btnUserLogin(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CoordinatorsLoginForm.fxml"));
                Pane userLoginPane = (Pane) fxmlLoader.load();
                adminLoginPane.getChildren().clear();
                adminLoginPane.getChildren().add(userLoginPane);
        }

        @FXML
        void txtPasswordOnAction(ActionEvent event) throws IOException {
                btnLogin(event);

        }

    }

