package lk.ijse.culinary.bo.custom;

import lk.ijse.culinary.bo.SuperBO;
import lk.ijse.culinary.dto.StudentCourseDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDto dto);
    boolean updateStudent(StudentDto dto);
    boolean deleteStudent(String id);
    List<StudentDto> getAllStudent();
    boolean isStudentExist(StudentDto dto);
    String getNewStudentId(String id) throws Exception;
    List<String> getIds();
    Student searchByContact(String id) throws SQLException, ClassNotFoundException;

    // New methods
    List<String> getAllStudentEmails();
    List<String> getAllCourseIDs();

    int getStudentCount();

    int getRegisteredStudentCount();

    List<StudentCourseDto> getAllStudentCourses();
}