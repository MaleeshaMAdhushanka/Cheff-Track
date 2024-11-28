// UpdatePaymentFormController.java
package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.PaymentBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.dto.PaymentDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.tm.PaymentTm;
import lk.ijse.culinary.util.ValidationUtil;
import lombok.Setter;

import java.time.LocalDate;
import java.util.function.Consumer;

public class UpdatePaymentFormController {

    @FXML
    private MFXDatePicker Date;

    @FXML
    private MFXButton btnCancel;

    @FXML
    private MFXButton btnConfirm;

    @FXML
    private MFXButton btnUpdatePay;

    @FXML
    private MFXFilterComboBox<String> cmbCourse;

    @FXML
    private MFXFilterComboBox<String> cmbStudentEmail;

    @FXML
    private Label lblPayID;

    @FXML
    private Label lblPayID1;

    @FXML
    private MFXTextField txtAmount;

    @FXML
    private MFXTextField txtCourseFee;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXTextField txtStatus;
    @Setter
    private CoursePaymentFormController coursePaymentFormController;

    private Consumer<Void> onCloseCallback;

    private PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() {
        loadAllCourses();
        loadAllStudents();
    }

    private void loadAllStudents() {
        try {
            StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
            cmbStudentEmail.getItems().clear();
            for (StudentDto studentDto : studentBO.getAllStudent()) {
                cmbStudentEmail.getItems().add(studentDto.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllCourses() {
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
    void cmbStudentEmailOnAction(ActionEvent event) {
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

    @FXML
    void btnUpdatePayOnAction(ActionEvent event) {
        if (validateFields()) {
            String paymentId = lblPayID.getText();
            String studentEmail = cmbStudentEmail.getSelectionModel().getSelectedItem();
            String courseId = cmbCourse.getSelectionModel().getSelectedItem();
            LocalDate paymentDate = Date.getValue();
            double upfrontAmount = Double.parseDouble(txtAmount.getText());
            double courseFee = Double.parseDouble(txtCourseFee.getText());
            double balanceAmount = courseFee - upfrontAmount;

            PaymentDto paymentDto = new PaymentDto(paymentId, paymentDate, upfrontAmount, balanceAmount, "Paid", studentEmail, courseId, upfrontAmount);

            try {
                boolean isSaved = paymentBO.updatePayment(paymentDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment Successful!").show();
                    coursePaymentFormController.addNewPayment(paymentDto);
                    closeTheWindow();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment Failed!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while processing the payment.").show();
            }
        }
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        // Handle the confirm button action here
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (cmbStudentEmail.getSelectionModel().getSelectedItem() == null) {
            cmbStudentEmail.getStyleClass().add("text-field-error");
            cmbStudentEmail.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            cmbStudentEmail.getStyleClass().remove("text-field-error");
            cmbStudentEmail.getStyleClass().add("text-field-success");
        }

        if (cmbCourse.getSelectionModel().getSelectedItem() == null) {
            cmbCourse.getStyleClass().add("text-field-error");
            cmbCourse.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            cmbCourse.getStyleClass().remove("text-field-error");
            cmbCourse.getStyleClass().add("text-field-success");
        }

        if (Date.getValue() == null) {
            Date.getStyleClass().add("text-field-error");
            Date.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            Date.getStyleClass().remove("text-field-error");
            Date.getStyleClass().add("text-field-success");
        }

        if (txtAmount.getText().trim().isEmpty() || !ValidationUtil.isNumeric(txtAmount.getText())) {
            txtAmount.getStyleClass().add("text-field-error");
            txtAmount.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtAmount.getStyleClass().remove("text-field-error");
            txtAmount.getStyleClass().add("text-field-success");
        }

        if (txtCourseFee.getText().trim().isEmpty() || !ValidationUtil.isNumeric(txtCourseFee.getText())) {
            txtCourseFee.getStyleClass().add("text-field-error");
            txtCourseFee.getStyleClass().remove("text-field-success");
            isValid = false;
        } else {
            txtCourseFee.getStyleClass().remove("text-field-error");
            txtCourseFee.getStyleClass().add("text-field-success");
        }

        return isValid;
    }

    public void setCoursePaymentDetails(PaymentTm selectedPayment) {
        lblPayID.setText(selectedPayment.getPaymentID());
        loadAllStudents(); // Ensure items are loaded
        loadAllCourses();  // Ensure items are loaded

        if (cmbStudentEmail.getItems().contains(selectedPayment.getStudentEmail())) {
            cmbStudentEmail.getSelectionModel().selectItem(selectedPayment.getStudentEmail());
        } else {
            new Alert(Alert.AlertType.ERROR, "Student email not found in the list!").show();
        }

        if (cmbCourse.getItems().contains(selectedPayment.getCourseID())) {
            cmbCourse.getSelectionModel().selectItem(selectedPayment.getCourseID());
        } else {
            new Alert(Alert.AlertType.ERROR, "Course ID not found in the list!").show();
        }

        if (selectedPayment.getPaymentDate() != null) {
            Date.setValue(selectedPayment.getPaymentDate().toLocalDate());
        } else {
            Date.setValue(null);
        }

        txtAmount.setText(String.valueOf(selectedPayment.getUpfrontAmount()));
        txtCourseFee.setText(String.valueOf(selectedPayment.getBalanceAmount()));
        txtName.setText(selectedPayment.getStudentName() != null ? new String(selectedPayment.getStudentName()) : "");
        txtStatus.setText(selectedPayment.getStatus() != null ? selectedPayment.getStatus() : "");
    }
}