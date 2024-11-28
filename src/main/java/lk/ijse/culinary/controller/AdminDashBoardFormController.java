package lk.ijse.culinary.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.culinary.bo.BOFactory;
import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.bo.custom.StudentCourseBO;
import lk.ijse.culinary.dto.StudentCourseDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminDashBoardFormController {

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
    private Label lblDate;
    @FXML
    private Label lblTime;


    @FXML
    private TableView<StudentCourseDto> tblStudentCourses;

    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);
    private StudentCourseBO studentcourseBO = (StudentCourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT_COURSE);

    public void initialize() {
        loadStudentCourses();
        loadCounts();
        setDateAndTime();
    }

    private void setDateAndTime() {
        new Thread(() -> {
            while (true) {
                try {
                    String date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
                    String time = new SimpleDateFormat("hh:mm:ss a").format(new Date());
                    Platform.runLater(() -> {
                        lblDate.setText(date);
                        lblTime.setText(time);
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loadStudentCourses() {
        try {
            List<StudentCourseDto> studentCourses = studentcourseBO.getStudentCourses();
            ObservableList<StudentCourseDto> studentCourseList = FXCollections.observableArrayList(studentCourses);
            tblStudentCourses.setItems(studentCourseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCounts() {
        try {
            int coursesCount = courseBO.getCourseCount();
            int studentsCount = studentBO.getStudentCount();
            int registerStudentCount = studentBO.getRegisteredStudentCount();

            lblCoursesCount.setText(String.valueOf(coursesCount));
            lblStudentsCount.setText(String.valueOf(studentsCount));
            lblRegisterStudentCount.setText(String.valueOf(registerStudentCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}