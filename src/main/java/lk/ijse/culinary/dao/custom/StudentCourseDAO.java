package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.StudentCourse;

public interface StudentCourseDAO extends CrudDAO<StudentCourse> {
    StudentCourse getStudentCourseById(String  value);
}
