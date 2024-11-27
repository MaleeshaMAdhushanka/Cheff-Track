package lk.ijse.culinary.bo.custom;

import lk.ijse.culinary.bo.SuperBO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDto dto);
    boolean updateCourse(CourseDto dto);
    boolean deleteCourse(String id);
    List<CourseDto> getAllCourse();
    boolean isCourseExist(CourseDto dto);
    public String generateNextId() throws SQLException, ClassNotFoundException;
    List<String> getIds();

    public Course searchById(String id) throws SQLException, ClassNotFoundException;

    Course searchByName(String courseName) throws SQLException, ClassNotFoundException;

    List<String> getAllCourseIDs();

    int getCourseCount();
}
