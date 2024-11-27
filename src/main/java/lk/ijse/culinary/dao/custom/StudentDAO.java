package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.Student;

import java.util.List;

public interface StudentDAO  extends CrudDAO<Student> {
    Student searchStudentByContact(String id);
    List<Student> searchByContact2(String id);
}
