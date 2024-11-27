// CourseFormController.java
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
import lk.ijse.culinary.bo.custom.impl.CourseBOImpl;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.tm.CourseTm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseFormController {

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private MFXButton btnUpdate;

    @FXML
    private TableColumn<CourseTm, String> colDuration;

    @FXML
    private TableColumn<CourseTm, String> colFee;

    @FXML
    private TableColumn<CourseTm, String> colId;

    @FXML
    private TableColumn<CourseTm, String> colName;

    @FXML
    private TableColumn<CourseTm, MFXButton> colRemove;

    @FXML
    private TableView<CourseTm> tblCourse;

    @FXML
    private MFXTextField txtSearch;
    private List<CourseDto> courseList = new ArrayList<>();
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
    public void initialize() {
        refreshTable();
        fetchAllCourses();
        setCellValueFactory();
//        loadCourseTable();
        tblCourse.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && tblCourse.getSelectionModel().getSelectedItem() != null) {
                CourseTm selectedCourse = tblCourse.getSelectionModel().getSelectedItem();
                openUpdateCourseForm(selectedCourse);
            }
        });

        // Apply CSS to the TableView
        tblCourse.getStylesheets().add(getClass().getResource("/assets/css/TableStyle.css").toExternalForm());
    }
    public void refreshTable() {
        try {
            List<CourseDto> courseDtos = courseBO.getAllCourse();
            List<CourseTm> courseTms = courseDtos.stream()
                    .map(dto -> new CourseTm(
                            dto.getCourseID(),
                            dto.getCourseName(),
                            dto.getCourseDuration(),
                            dto.getCourseFee(),
                            createRemoveButton(dto.getCourseID())
                    ))
                    .sorted(Comparator.comparing(CourseTm::getCourseID)) // Sort by Course ID
                    .collect(Collectors.toList());
            tblCourse.getItems().clear();
            tblCourse.getItems().addAll(courseTms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void openUpdateCourseForm(CourseTm selectedCourse) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/updateCourseForm.fxml"));
            Parent rootNode = loader.load();

            UpdateCourseFormController controller = loader.getController();
            controller.setCourseFormController(this);
            controller.setCourseDetails(selectedCourse);

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Update Course");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(CourseDto updatedCourse) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(updatedCourse.getCourseID())) {
                courseList.set(i, updatedCourse);
                break;
            }
        }
//        loadCourseTable();
    }

    private void fetchAllCourses() {
        CourseBOImpl courseBO = new CourseBOImpl();
        courseList = courseBO.getAllCourse();
    }

//    private void loadCourseTable() {
//        ObservableList<CourseTm> tmList = FXCollections.observableArrayList();
//
//        for (CourseDto course : courseList) {
//            MFXButton removeButton = createRemoveButton(course.getCourseID());
//
//            CourseTm courseTm = new CourseTm(
//                    course.getCourseID(),
//                    course.getCourseName(),
//                    course.getCourseDuration(),
//                    course.getCourseFee(),
//                    removeButton
//            );
//            tmList.add(courseTm);
//        }
//        tblCourse.setItems(tmList);
//    }

    private MFXButton createRemoveButton(String courseId) {
        MFXButton removeButton = new MFXButton();
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/images/remove.png"))));
        icon.setFitHeight(30);
        icon.setFitWidth(30);
        removeButton.setGraphic(icon);
        removeButton.setText("");
        removeButton.setOnAction(event -> showConfirmationAlert(courseId));
        return removeButton;
    }

    private void showConfirmationAlert(String courseId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove this course?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                removeCourse(courseId);
            }
        });
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    private void removeCourse(String courseId) {
        CourseBOImpl courseBO = new CourseBOImpl();
        boolean isDeleted = courseBO.deleteCourse(courseId);
        if (isDeleted) {
            fetchAllCourses();
//            loadCourseTable();
            refreshTable();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/courseDataForm.fxml"));
        Parent rootNode = loader.load();
        CourseDataFormController controller = loader.getController();
        controller.setCourseFormController(this);
        controller.setOnCloseCallback(aVoid -> refreshTable());
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Course");
        stage.show();
    }


    public void addNewCourse(CourseDto newCourse) {
        courseList.add(newCourse);
//        loadCourseTable();
        refreshTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String courseId = txtSearch.getText().trim();
        if (!courseId.isEmpty()) {
            CourseTm selectedCourse = searchCourseById(courseId);
            if (selectedCourse != null) {
                openUpdateCourseForm(selectedCourse);
            } else {
                new Alert(Alert.AlertType.WARNING, "Course ID not found").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID to search").show();
        }
    }
    // CourseFormController.java
    private CourseTm searchCourseById(String courseId) {
        for (CourseTm course : tblCourse.getItems()) {
            if (course.getCourseID().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}