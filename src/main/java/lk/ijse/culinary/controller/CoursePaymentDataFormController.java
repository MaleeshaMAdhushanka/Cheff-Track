// CoursePaymentDataFormController.java
package lk.ijse.culinary.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.PaymentBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.bo.custom.StudentCourseBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.StudentCourseDAO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.dto.PaymentDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.entity.Payment;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.entity.StudentCourse;
import lk.ijse.culinary.util.ValidationUtil;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CoursePaymentDataFormController {

    @FXML
    private MFXDatePicker Date;

    @FXML
    private MFXButton btnCancel;

    @FXML
    private MFXButton btnPay;

    @FXML
    private MFXFilterComboBox<String> cmbCourse;

    @FXML
    private MFXFilterComboBox<String> cmbPayHistory;

    @FXML
    private MFXFilterComboBox<String> cmbStudentEmail;

    @FXML
    private Label lblBalanceAmount;

    @FXML
    private Label lblPayDate;
    @FXML
    private MFXButton btnConfirm;
    @FXML
    private Label lblPayID;

    @FXML
    private Label lblPayStatus;

    @FXML
    private Label lblUpFrontPayment;

    @FXML
    private MFXTextField txtAmount;

    @FXML
    private MFXTextField txtCourseFee;

    @FXML
    private MFXTextField txtName;
    @FXML
    private MFXTextField txtStuCouDetail;
    @FXML
    private MFXTextField txtStatus;
    @Setter
    private CoursePaymentFormController coursePaymentFormController;
    StudentCourseDAO studentCourseDAO = (StudentCourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT_COURSE);
    ArrayList<Payment> paymentArrayList = new ArrayList<>();
    ArrayList<StudentCourse> studentCourseArrayList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();
    private Consumer<Void> onCloseCallback;
    ObservableList<String> studentObservableList = FXCollections.observableArrayList();
    ObservableList<String> paymentObservableList = FXCollections.observableArrayList();
    private PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private StudentCourseBO studentCourseBO = (StudentCourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT_COURSE);

    public void setOnCloseCallback(Consumer<Void> onCloseCallback) {
        this.onCloseCallback = onCloseCallback;
    }

    public void initialize() throws IOException {
        loadAllCourses();
        loadAllStudents();
        getAllStudentCourses();
        // searchStudent();
        // searchPayment();
        //setDate();
        try {
            String newPaymentId = paymentBO.generateNextId();
            lblPayID.setText(newPaymentId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbCourse.setOnAction(this::cmbCourseOnAction);
        cmbStudentEmail.setOnAction(this::cmbStudentEmailOnAction);

        // Initially disable the pay button
        btnPay.setDisable(true);
    }

    private void setDate() {
        Date.setValue(LocalDate.now());
    }

    private void searchPayment() {
        for (Payment payment : paymentArrayList) {
            paymentObservableList.add(String.valueOf(payment.getStudentCourse().getStudentCourseID()));
        }
        cmbPayHistory.setItems(paymentObservableList);
    }

    private void searchStudent() {
        for (Student student : studentArrayList) {
            studentObservableList.add(student.getEmail());
        }
        cmbStudentEmail.setItems(studentObservableList);
    }

    private void getAllStudentCourses() throws IOException {
        List<StudentCourse> studentCourseList = studentCourseBO.getStudentCourseList();
        studentCourseArrayList = (ArrayList<StudentCourse>) studentCourseList;
    }

    private void loadAllStudents() {
        try {
            cmbStudentEmail.getItems().clear();
            List<StudentDto> studentList = studentBO.getAllStudent();
            for (StudentDto student : studentList) {
                cmbStudentEmail.getItems().add(student.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllCourses() {
        try {
            cmbCourse.getItems().clear();
            List<CourseDto> courseList = courseBO.getAllCourse();
            for (CourseDto courseDto : courseList) {
                cmbCourse.getItems().add(courseDto.getCourseID());
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
        String selectedEmail = cmbStudentEmail.getSelectionModel().getSelectedItem();
        if (selectedEmail != null) {
            try {
                StudentDto student = studentBO.getAllStudent().stream()
                        .filter(s -> s.getEmail().equals(selectedEmail))
                        .findFirst()
                        .orElse(null);
                if (student != null) {
                    txtName.setText(student.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cmbCourseOnAction(ActionEvent event) {
        String selectedCourseID = cmbCourse.getSelectionModel().getSelectedItem();
        if (selectedCourseID != null) {
            try {
                CourseDto course = courseBO.getAllCourse().stream()
                        .filter(c -> c.getCourseID().equals(selectedCourseID))
                        .findFirst()
                        .orElse(null);
                if (course != null) {
                    txtCourseFee.setText(String.valueOf(course.getCourseFee()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cmbPayHistoryOnAction(ActionEvent event) {
        String stu_cou_id = cmbPayHistory.getSelectionModel().getSelectedItem();

        for (Payment payment : paymentArrayList) {
            if (payment.getStudentCourse().getStudentCourseID().equals(stu_cou_id)) {
                lblBalanceAmount.setText(String.valueOf(payment.getBalanceAmount()));
                lblPayDate.setText(String.valueOf(payment.getPaymentDate()));
                lblPayStatus.setText(payment.getStatus());
                lblUpFrontPayment.setText(String.valueOf(payment.getUpfrontAmount()));
            }
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
    void btnUpdateOnAction(ActionEvent event) {
        // Handle the update action
    }

//    public void setCoursePaymentDetails(PaymentTm selectedPayment) {
//        lblPayID.setText(selectedPayment.getPaymentID());
//        cmbStudentEmail.getSelectionModel().select(selectedPayment.getStudentEmail());
//        txtName.setText(selectedPayment.getStudentName());
//        cmbCourse.getSelectionModel().select(selectedPayment.getCourseID());
//        txtCourseFee.setText(selectedPayment.getCourseFee() + "");
//    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
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
                boolean isSaved = paymentBO.savePayment(paymentDto);
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

    private boolean validateFields() {
        if (!ValidationUtil.isNotEmpty(String.valueOf(txtAmount))) {
            new Alert(Alert.AlertType.ERROR, "Amount field cannot be empty!").show();
            return false;
        }
        if (!ValidationUtil.isNumeric(txtAmount)) {
            new Alert(Alert.AlertType.ERROR, "Amount must be a number!").show();
            return false;
        }
        if (!ValidationUtil.isNotEmpty(String.valueOf(txtCourseFee))) {
            new Alert(Alert.AlertType.ERROR, "Course Fee field cannot be empty!").show();
            return false;
        }
        if (!ValidationUtil.isNumeric(txtCourseFee)) {
            new Alert(Alert.AlertType.ERROR, "Course Fee must be a number!").show();
            return false;
        }
        if (!ValidationUtil.isNotEmpty(String.valueOf(txtName))) {
            new Alert(Alert.AlertType.ERROR, "Name field cannot be empty!").show();
            return false;
        }
        if (!ValidationUtil.isNotEmpty(cmbStudentEmail.getSelectedItem())) {
            new Alert(Alert.AlertType.ERROR, "Student Email field cannot be empty!").show();
            return false;
        }
        if (!ValidationUtil.isValidEmail(cmbStudentEmail.getSelectedItem())) {
            new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String selectedEmail = cmbStudentEmail.getSelectionModel().getSelectedItem();
        if (selectedEmail != null) {
            try {
                StudentDto student = studentBO.getAllStudent().stream()
                        .filter(s -> s.getEmail().equals(selectedEmail))
                        .findFirst()
                        .orElse(null);
                if (student != null) {
                    txtName.setText(student.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String selectedCourseID = cmbCourse.getSelectionModel().getSelectedItem();
        if (selectedCourseID != null) {
            try {
                CourseDto course = courseBO.getAllCourse().stream()
                        .filter(c -> c.getCourseID().equals(selectedCourseID))
                        .findFirst()
                        .orElse(null);
                if (course != null) {
                    txtCourseFee.setText(String.valueOf(course.getCourseFee()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Details confirmed. You can now proceed to make the payment.");
        alert.showAndWait();

        // Enable the pay button
        btnPay.setDisable(false);
    }
}