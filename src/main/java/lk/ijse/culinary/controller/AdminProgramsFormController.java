package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class AdminProgramsFormController {
                @FXML
                private TableColumn<?, ?> colDuration;

                @FXML
                private TableColumn<?, ?> colFee;

                @FXML
                private TableColumn<?, ?> colId;

                @FXML
                private TableColumn<?, ?> colProgramName;

                @FXML
                private TableColumn<?, ?> colRemove;

                @FXML
                private TableColumn<?, ?> colType;

                @FXML
                private TableColumn<?, ?> colUpdate;

                @FXML
                private TableView<?> tblBook;

                @FXML
                private MFXTextField txtSearch;

                @FXML
                void btnAddOnAction(ActionEvent event) {

                }

                @FXML
                void btnSearchOnAction(ActionEvent event) {

                }

        }


