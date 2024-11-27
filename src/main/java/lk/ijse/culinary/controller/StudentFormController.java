package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.bo.custom.impl.StudentBOImpl;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.tm.StudentTm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentFormController {

    @FXML
    private AnchorPane StudentPane;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnRemove;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private MFXButton btnUpdate;

    @FXML
    private TableColumn<StudentTm,String> colAddress;

    @FXML
    private TableColumn<StudentTm,String> colContact;

    @FXML
    private TableColumn<StudentTm,String> colDob;

    @FXML
    private TableColumn<StudentTm,String> colEmail;

    @FXML
    private TableColumn<StudentTm,String> colId;

    @FXML
    private TableColumn<StudentTm,String> colName;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private MFXTextField txtSearch;
    @FXML
    private TableColumn<StudentTm,String> colRemove;
    private List<StudentDto> studentList = new ArrayList<>();

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    public void initialize(){
        refreshTable();
//        studentList = new ArrayList<>();
        fetchAllStudents();
        setCellValueFactory();
//        loadStudentsTable();
        tblStudent.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && tblStudent.getSelectionModel().getSelectedItem() != null) {
                StudentTm selectedCourse = tblStudent.getSelectionModel().getSelectedItem();
                openUpdateStudentForm(selectedCourse);
            }
        });

        // Apply CSS to the TableView
        tblStudent.getStylesheets().add(getClass().getResource("/assets/css/TableStyle.css").toExternalForm());
    }
    public void refreshTable() {
        try {
            List<StudentDto> studentDtos = studentBO.getAllStudent();
            List<StudentTm> studentTms = studentDtos.stream()
                    .map(dto -> new StudentTm(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getDob(),
                            dto.getEmail(),
                            dto.getContact(),
                            dto.getCourse(),
                            createRemoveButton(dto.getId())
                    ))
                    .sorted(Comparator.comparing(StudentTm::getId)) // Sort by Student ID
                    .collect(Collectors.toList());
            tblStudent.getItems().clear();
            tblStudent.getItems().addAll(studentTms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUpdateStudentForm(StudentTm selectedStudent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/updateStudentForm.fxml"));
            Parent rootNode = loader.load();

            UpdateStudentFormController controller = loader.getController();
            controller.setStudentFormController(this);
            controller.setStudentDetails(selectedStudent);

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Update Student");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(StudentDto updatedStudent) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(updatedStudent.getId())) {
                studentList.set(i, updatedStudent);
                break;
            }
        }
//        loadStudentsTable();
    }

//    private void loadStudentsTable() {
//        ObservableList<StudentTm> tmList = FXCollections.observableArrayList();
//
//        for (StudentDto student : studentList) {
//            MFXButton removeButton = createRemoveButton(student.getId());
//            StudentTm studentTm = new StudentTm(
//                    student.getId(),
//                    student.getName(),
//                    student.getAddress(),
//                    student.getDob(),
//                    student.getEmail(),
//                    student.getContact(),
//                    student.getCourse(),
//                    removeButton
//            );
//            tmList.add(studentTm);
//        }
//        tblStudent.setItems(tmList);
//    }

    private MFXButton createRemoveButton(String studentId) {
        MFXButton removeButton = new MFXButton();
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/remove.png"))));
        icon.setFitHeight(30);
        icon.setFitWidth(30);
        removeButton.setGraphic(icon);
        removeButton.setText("");
        removeButton.setOnAction(event -> showConfirmationAlert(studentId));
        return removeButton;
    }

    private void showConfirmationAlert(String studentId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove this student?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                removeStudent(studentId);
            }
        });
    }

    private void removeStudent(String studentId) {
        boolean isDeleted = studentBO.deleteStudent(studentId);
        if (isDeleted) {
            refreshTable();
        }
    }

    private void fetchAllStudents() {
        StudentBOImpl studentBO = new StudentBOImpl();
        studentList = studentBO.getAllStudent();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/studentDataForm.fxml"));
        Parent rootNode = loader.load();
        StudentDataFormController controller = loader.getController();
        controller.setStudentFormController(this);
        controller.setOnCloseCallback(aVoid -> refreshTable());
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Student");
        stage.show();
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean isDeleted = new StudentBOImpl().deleteStudent(selectedStudent.getId());
            if (isDeleted) {
                studentList.removeIf(student -> student.getId().equals(selectedStudent.getId()));
//                loadStudentsTable();
                refreshTable();
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete student").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a student to delete").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String studentId = txtSearch.getText().trim();
        if (!studentId.isEmpty()) {
            StudentTm selectedCourse = searchStudentById(studentId);
            if (selectedCourse != null) {
                openUpdateStudentForm(selectedCourse);
            } else {
                new Alert(Alert.AlertType.WARNING, "Course ID not found").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID to search").show();
        }
    }

    private StudentTm searchStudentById(String studentId) {
        for (StudentTm student : tblStudent.getItems()) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/updateStudentForm.fxml"));
        Parent rootNode = loader.load();
        UpdateStudentFormController controller = loader.getController();
        controller.setStudentFormController(this);
        controller.setOnCloseCallback(aVoid -> refreshTable());
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Update Student");
        stage.show();
    }
}