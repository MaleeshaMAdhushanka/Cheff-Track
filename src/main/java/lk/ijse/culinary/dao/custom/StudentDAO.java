package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.dto.StudentCourseDto;
import lk.ijse.culinary.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<String> getAllStudentEmails();

    List<String> getAllCourseIDs();

    Student searchByContact(String id);
    List<Student> searchByContact2(String id);

    Student findByEmail(String studentEmail);

    int getStudentCount();

    int getRegisteredStudentCount();

    List<StudentCourseDto> getAllStudentCourses();
}
