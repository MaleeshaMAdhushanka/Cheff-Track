// CoursePaymentFormController.java
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
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.PaymentBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.bo.custom.StudentCourseBO;
import lk.ijse.culinary.bo.custom.impl.PaymentBOImpl;
import lk.ijse.culinary.dto.PaymentDto;
import lk.ijse.culinary.tm.PaymentTm;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CoursePaymentFormController {

    @FXML
    private TableColumn<PaymentTm, String> ColBalancePay;

    @FXML
    private TableColumn<PaymentTm, String> ColCourseID;

    @FXML
    private TableColumn<PaymentTm, String> ColStudentCourseID;

    @FXML
    private TableColumn<PaymentTm, String> ColStudentEmail;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private TableColumn<PaymentTm, String> colID;

    @FXML
    private TableColumn<PaymentTm, String> colRemove;

    @FXML
    private TableColumn<PaymentTm, String> colStatus;

    @FXML
    private TableColumn<PaymentTm, String> colUpFrontpay;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private MFXTextField txtSearch;
    @Setter
    private CoursePaymentFormController coursePaymentFormController;

    private List<PaymentDto> paymenttList = new ArrayList<>();

    public CoursePaymentFormController() {
        // Initialize the paymenttList
        this.paymenttList = new ArrayList<>();
    }

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    StudentCourseBO studentCourseBO = (StudentCourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT_COURSE);

    public void initialize() {
        refreshTable();
        fetchAllPayments();
        setCellValueFactory();
        tblPayment.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && tblPayment.getSelectionModel().getSelectedItem() != null) {
                PaymentTm selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
                openUpdatePaymentForm(selectedPayment);
            }
        });

        // Apply CSS to the TableView
        tblPayment.getStylesheets().add(getClass().getResource("/assets/css/TableStyle.css").toExternalForm());
    }

    private void openUpdatePaymentForm(PaymentTm selectedPayment) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/updatePaymentForm.fxml"));
            Parent rootNode = loader.load();

            UpdatePaymentFormController controller = loader.getController();
            controller.setCoursePaymentFormController(this);
            controller.setCoursePaymentDetails(selectedPayment);

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Update Payment");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colUpFrontpay.setCellValueFactory(new PropertyValueFactory<>("upfrontAmount"));
        ColBalancePay.setCellValueFactory(new PropertyValueFactory<>("balanceAmount"));
        ColCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        ColStudentEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//        ColStudentCourseID.setCellValueFactory(new PropertyValueFactory<>("studentCourse"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    private void fetchAllPayments() {
        PaymentBOImpl paymentBO = new PaymentBOImpl();
        paymenttList = paymentBO.getAllPayment();
    }

    public void addNewPayment(PaymentDto paymentDto) {
        paymenttList.add(paymentDto);
        refreshTable();
    }

    public void refreshTable() {
        try {
            List<PaymentDto> paymentDtos = paymentBO.getAllPayment();
            List<PaymentTm> paymentTms = paymentDtos.stream()
                    .map(dto -> new PaymentTm(
                            dto.getPaymentID(),
                            dto.getUpfrontAmount(),
                            dto.getBalanceAmount(),
                            dto.getCourseID(),
                            dto.getStudentEmail(),
                            dto.getStatus(),
                            createRemoveButton(dto.getPaymentID())
                    ))
                    .sorted(Comparator.comparing(PaymentTm::getPaymentID)) // Sort by Payment ID
                    .collect(Collectors.toList());
            tblPayment.getItems().clear();
            tblPayment.getItems().addAll(paymentTms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MFXButton createRemoveButton(String paymentID) {
        MFXButton removeButton = new MFXButton();
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/remove.png"))));
        icon.setFitHeight(30);
        icon.setFitWidth(30);
        removeButton.setGraphic(icon);
        removeButton.setText("");
        removeButton.setOnAction(event -> showConfirmationAlert(paymentID));
        return removeButton;
    }

    private void showConfirmationAlert(String paymentId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove this course?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                removePayment(paymentId);
            }
        });
    }

    private void removePayment(String paymentId) {
        PaymentBOImpl paymentBO = new PaymentBOImpl();
        boolean isDeleted = paymentBO.deletePayment(paymentId);
        if (isDeleted) {
            fetchAllPayments();
            refreshTable();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/coursePaymentDataForm.fxml"));
        Parent rootNode = loader.load();
        CoursePaymentDataFormController controller = loader.getController();
        controller.setCoursePaymentFormController(this);
        controller.setOnCloseCallback(aVoid -> refreshTable());
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Payment");
        stage.show();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String paymentId = txtSearch.getText().trim();
        if (!paymentId.isEmpty()) {
            PaymentTm selectedPayment = searchPaymentbyID(paymentId);
            if (selectedPayment != null) {
                openUpdatePaymentForm(selectedPayment);
            } else {
                new Alert(Alert.AlertType.WARNING, "Course ID not found").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID to search").show();
        }
    }

    private PaymentTm searchPaymentbyID(String paymentId) {
        for (PaymentTm payment : tblPayment.getItems()) {
            if (payment.getPaymentID().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }
}