package lk.ijse.culinary.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.dto.StudentCourseDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDashBoardFormController {

    @FXML
    private TableColumn<StudentCourseDto, String> colCourseName;

    @FXML
    private TableColumn<StudentCourseDto, String> colDuration;

    @FXML
    private TableColumn<StudentCourseDto, String> colID;

    @FXML
    private TableColumn<StudentCourseDto, String> colName;

    @FXML
    private TableColumn<StudentCourseDto, String> colRegisteredDate;

    @FXML
    private TableColumn<StudentCourseDto, String> colRestPayAmount;

    @FXML
    private AnchorPane dashBoardPane;

    @FXML
    private Label lblCoursesCount;

    @FXML
    private Label lblRegisterStudentCount;

    @FXML
    private Label lblStudentsCount;


    @FXML
    private TableView<StudentCourseDto> tblStudentCourses;

    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() {
        loadStudentCourses();
        loadCounts();

    }


    private void loadStudentCourses() {
        try {
            List<StudentCourseDto> studentCourses = studentBO.getAllStudentCourses();
            ObservableList<StudentCourseDto> studentCourseList = FXCollections.observableArrayList(studentCourses);
            tblStudentCourses.setItems(studentCourseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCounts() {
        try {
            int studentCount = studentBO.getStudentCount();
            int registeredStudentCount = studentBO.getRegisteredStudentCount();
            int courseCount = courseBO.getCourseCount();

            lblStudentsCount.setText(String.valueOf(studentCount));
            lblRegisterStudentCount.setText(String.valueOf(registeredStudentCount));
            lblCoursesCount.setText(String.valueOf(courseCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}