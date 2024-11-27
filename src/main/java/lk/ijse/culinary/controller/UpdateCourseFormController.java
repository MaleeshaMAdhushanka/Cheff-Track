// UpdateCourseFormController.java
package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.tm.CourseTm;
import lombok.Setter;

import java.util.function.Consumer;

public class UpdateCourseFormController {

    @FXML
    private MFXTextField txtCourseId;

    @FXML
    private MFXTextField txtCourseName;

    @FXML
    private MFXTextField txtCourseDuration;

    @FXML
    private MFXTextField txtCourseFee;

    @FXML
    private MFXButton btnUpdate;

    @FXML
    private MFXButton btnCancel;

    @Setter
    private CourseFormController courseFormController;
    private Consumer<Void> onCloseCallback;
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
    public void setOnCloseCallback(Consumer<Void> onCloseCallback) {
        this.onCloseCallback = onCloseCallback;
    }
    public void setCourseDetails(CourseTm courseTm) {
        txtCourseId.setText(courseTm.getCourseID());
        txtCourseName.setText(courseTm.getCourseName());
        txtCourseDuration.setText(courseTm.getCourseDuration());
        txtCourseFee.setText(String.valueOf(courseTm.getCourseFee()));
    }

    @FXML
    void btnUpdateOnction(ActionEvent event) {
        String courseId = txtCourseId.getText();
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
            boolean isUpdated = courseBO.updateCourse(courseDto);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course Updated Successfully").show();
                if (courseFormController != null) {
                    courseFormController.refreshTable();
                }
                closeTheWindow();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the course").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update the course").show();
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
    void btnCancelOnAction(ActionEvent event) {
        closeTheWindow();
    }
}