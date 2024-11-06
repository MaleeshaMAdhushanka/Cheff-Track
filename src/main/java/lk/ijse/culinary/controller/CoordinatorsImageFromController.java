package lk.ijse.culinary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;


public class CoordinatorsImageFromController {


        @FXML
        private Circle circleImg;

        @FXML
        private Label lblError;

        @FXML
        private AnchorPane registerPane;

        @FXML
        void btnLogin(MouseEvent event) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CoordinatorsLoginForm.fxml"));
                Pane loginPane = (Pane) fxmlLoader.load();
                registerPane.getChildren().clear();
                registerPane.getChildren().add(loginPane);

        }

        @FXML
        void btnNextOnAction(ActionEvent event) throws IOException {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CoordinatorsRegisterForm.fxml"));
                Pane loginPane = (Pane) fxmlLoader.load();
                registerPane.getChildren().clear();
                registerPane.getChildren().add(loginPane);


        }

        @FXML
        void circleImgOnAction(MouseEvent event) {

        }

    }



