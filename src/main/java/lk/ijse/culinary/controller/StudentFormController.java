package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


public class StudentFormController {

                @FXML
                private TableColumn<?, ?> colAdress;

                @FXML
                private TableColumn<?, ?> colEmail;

                @FXML
                private TableColumn<?, ?> colFirstName;

                @FXML
                private TableColumn<?, ?> colId;

                @FXML
                private TableColumn<?, ?> colLastName;

                @FXML
                private TableColumn<?, ?> colRemove;

                @FXML
                private TableColumn<?, ?> colUpadate;

                @FXML
                private AnchorPane studentPane;

                @FXML
                private TableView<?> tblStudent;

                @FXML
                private MFXTextField txtSearch;

                @FXML
                void btnAddOnAction(ActionEvent event) {

                }

                @FXML
                void btnSearchOnAction(ActionEvent event) {

                }

        }

