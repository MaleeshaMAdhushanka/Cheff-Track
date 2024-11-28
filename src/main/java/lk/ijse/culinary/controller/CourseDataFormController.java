package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.util.ValidationUtil;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CourseDataFormController {

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnCancel;

    @FXML
    private Label lblAction;

    @FXML
    private MFXTextField txtCourseDuration;

    @FXML
    private MFXTextField txtCourseFee;
    @FXML
    private Label lblCourseID;
    @FXML
    private MFXTextField txtCourseName;
    private List<CourseDto> courseList = new ArrayList<>();
    @Setter
    private CourseFormController courseFormController;
    private Consumer<Void> onCloseCallback;

    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);

    public void setOnCloseCallback(Consumer<Void> onCloseCallback) {
        this.onCloseCallback = onCloseCallback;
    }

    public void initialize() {
        generateCourseID();
    }

    private void generateCourseID() {
        try {
            String newCourseId = courseBO.generateNextId();
            lblCourseID.setText(newCourseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddOnction(ActionEvent event) {
        if (validateFields()) {
            String courseId = lblCourseID.getText();
            String courseName = txtCourseName.getText();
            String courseDuration = txtCourseDuration.getText();
            String courseFeeText = txtCourseFee.getText();

            double courseFee;
            try {
                courseFee = Double.parseDouble(courseFeeText);
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid course fee").show();
                return;
            }

            CourseDto courseDto = new CourseDto(courseId, courseName, courseDuration, courseFee);

            try {
                boolean isAdded = courseBO.saveCourse(courseDto);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Added Successfully").show();
                    courseFormController.addNewCourse(courseDto);
                    clearFields();
                    generateCourseID(); // Generate new course ID after adding the course
                    closeTheWindow();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add the course").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while adding the course").show();
            }
        }
    }

    private boolean validateFields() {
        if (!ValidationUtil.isValidName(txtCourseName.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid course name").show();
            return false;
        }
        if (!ValidationUtil.isNotEmpty(txtCourseDuration.getText())) {
            new Alert(Alert.AlertType.ERROR, "Course duration cannot be empty").show();
            return false;
        }
        if (!ValidationUtil.isNumeric(txtCourseFee.getText())) {
            new Alert(Alert.AlertType.ERROR, "Course fee must be a number").show();
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtCourseName.clear();
        txtCourseDuration.clear();
        txtCourseFee.clear();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        closeTheWindow();
    }

    private void closeTheWindow() {
        if (onCloseCallback != null) {
            onCloseCallback.accept(null);
        }
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}