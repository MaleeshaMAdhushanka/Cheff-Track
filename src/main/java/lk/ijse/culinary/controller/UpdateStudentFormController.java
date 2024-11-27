package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.tm.StudentTm;
import lombok.Setter;

import java.util.function.Consumer;

public class UpdateStudentFormController {

    @FXML
    private MFXButton btnCancel;

    @FXML
    private MFXButton btnUpdate;

    @FXML
    private MFXComboBox<String> cmbCourse;

    @FXML
    private MFXTextField contact;

    @FXML
    private MFXDatePicker dateofbirth;

    @FXML
    private MFXTextField txtAddress;

    @FXML
    private MFXTextField txtEmail;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXTextField txtStudentId;

    @Setter
    private StudentFormController studentFormController;
    @Setter
    private Consumer<Void> onCloseCallback;

    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        loadCourses();
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
    void btnCancelOnAction(ActionEvent event) {
        closeTheWindow();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String studentId = txtStudentId.getText();
        String studentName = txtName.getText();
        String studentEmail = txtEmail.getText();
        String studentAddress = txtAddress.getText();
        String studentContact = contact.getText();
        String studentDOB = dateofbirth.getText();
        String studentCourse = cmbCourse.getSelectedItem();

        StudentDto updatedStudent = new StudentDto(
                studentId,
                studentName,
                studentAddress,
                studentDOB,
                studentEmail,
                studentContact,
                studentCourse
        );

        try {
            boolean isUpdated = studentBO.updateStudent(updatedStudent);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully").show();
                if ( studentFormController != null) {
                    studentFormController.refreshTable();
                }
                closeTheWindow();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the student").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating the student").show();
        }
    }

    private void closeTheWindow() {
        if (onCloseCallback != null) {
            onCloseCallback.accept(null);
        }
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cmbCourseOnAction(ActionEvent event) {
        // Handle the action event for cmbCourse here
    }

    public void setStudentDetails(StudentTm selectedStudent) {
        txtStudentId.setText(selectedStudent.getId());
        txtName.setText(selectedStudent.getName());
        txtAddress.setText(selectedStudent.getAddress());
        dateofbirth.setText(selectedStudent.getDob());
        txtEmail.setText(selectedStudent.getEmail());
        contact.setText(selectedStudent.getContact());
        if (cmbCourse.getItems().contains(selectedStudent.getCourse())) {
            cmbCourse.getSelectionModel().selectItem(selectedStudent.getCourse());
        }
    }
}