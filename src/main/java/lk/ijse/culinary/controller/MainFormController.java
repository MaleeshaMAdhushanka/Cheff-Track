package lk.ijse.culinary.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane holderPane;

    public  void  initialize() throws IOException {
       FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/CoordinatorsLoginForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        holderPane.getChildren().clear();
        holderPane.getChildren().add(registerPane);
    }

}
