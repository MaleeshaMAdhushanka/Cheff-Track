package lk.ijse.culinary.controller;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class CoordinatorsFormController {

                @FXML
                private TableColumn<?, ?> colAddress;

                @FXML
                private TableColumn<?, ?> colEmail;

                @FXML
                private TableColumn<?, ?> colName;

                @FXML
                private TableColumn<?, ?> colRemove;

                @FXML
                private TableColumn<?, ?> colUpdate;

                @FXML
                private TableView<?> tblCoordinators;

                @FXML
                private MFXTextField txtSearch;

                @FXML
                private AnchorPane userPane;

                @FXML
                void btnAddOnAction(ActionEvent event) {

                }

                @FXML
                void btnSearchOnAction(ActionEvent event) {

                }

        }



