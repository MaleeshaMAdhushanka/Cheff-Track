package lk.ijse.culinary.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;



public class AdminDashBoardFormController {


                @FXML
                private TableColumn<?, ?> colBookID;

                @FXML
                private TableColumn<?, ?> colBorrowDate;

                @FXML
                private TableColumn<?, ?> colID;

                @FXML
                private TableColumn<?, ?> colLateCount;

                @FXML
                private TableColumn<?, ?> colReturnDate;

                @FXML
                private TableColumn<?, ?> colUserID;

                @FXML
                private AnchorPane dashBoardPane;

                @FXML
                private Label lblProgramsCount;

                @FXML
                private Label lblStudentsCount;

                @FXML
                private Label lblUserCount;

                @FXML
                private TableView<?> tblBorrowBook;

        }




