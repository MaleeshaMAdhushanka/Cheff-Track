package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.util.ValidationUtil;
import lombok.Setter;

import java.util.function.Consumer;

public class StudentDataFormController {
    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnCancel;

    @FXML
    private MFXComboBox<String> cmbCourse;

    @FXML
    private MFXTextField contact;

    @FXML
    private MFXDatePicker dateofbirth;

    @FXML
    private Label lblAction;

    @FXML
    private Label lblstudentId;

    @FXML
    private MFXTextField txtAddress;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;

    @Setter
    private StudentFormController studentFormController;
    @Setter
    private Consumer<Void> onCloseCallback;

    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        loadCourses();
        try {
            String newStudentId = studentBO.getNewStudentId(String.valueOf(new StudentDto()));
            lblstudentId.setText(newStudentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnCloseCallback(Consumer<Void> onCloseCallback) {
        this.onCloseCallback = onCloseCallback;
    }

    private void loadCourses() {
        try {
            CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
            cmbCourse.getItems().clear();
            for (CourseDto courseDto : courseBO.getAllCourse()) {
                cmbCourse.getItems().add(courseDto.getCourseName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (validateFields()) {
            String studentId = lblstudentId.getText();
            String studentName = txtName.getText();
            String studentEmail = txtEmail.getText();
            String studentAddress = txtAddress.getText();
            String studentCourse = cmbCourse.getSelectedItem();
            String studentContact = contact.getText();
            String studentDob = dateofbirth.getValue().toString();

            StudentDto studentDto = new StudentDto(
                    studentId,
                    studentName,
                    studentAddress,
                    studentDob,
                    studentEmail,
                    studentContact,
                    studentCourse
            );

            try {
                boolean isAdded = studentBO.saveStudent(studentDto);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully").show();
                    if (studentFormController != null) {
                        studentFormController.refreshTable();
                    }
                    closeTheWindow();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add the student").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to add the student").show();
            }
        }
    }

    private boolean validateFields() {
        if (!ValidationUtil.isValidName(txtName.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid student name").show();
            return false;
        }
        if (!ValidationUtil.isValidEmail(txtEmail.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid student email").show();
            return false;
        }
        if (!ValidationUtil.isValidAddress(txtAddress.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid student address").show();
            return false;
        }
        if (cmbCourse.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.ERROR, "No course selected").show();
            return false;
        }
        if (!ValidationUtil.isValidContact(contact.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid student contact").show();
            return false;
        }
        if (dateofbirth.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Student date of birth is empty").show();
            return false;
        }
        return true;
    }

    private void closeTheWindow() {
        if (onCloseCallback != null) {
            onCloseCallback.accept(null);
        }
        Stage stage = (Stage) lblstudentId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeTheWindow();
    }

    @FXML
    void cmbCourseOnAction(ActionEvent event) {
        String selectedCourse = cmbCourse.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            System.out.println("Selected course: " + selectedCourse);
        } else {
            new Alert(Alert.AlertType.ERROR, "No course selected").show();
        }
    }
}