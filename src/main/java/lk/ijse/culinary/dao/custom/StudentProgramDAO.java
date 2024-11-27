package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.Student_Program;

public interface StudentProgramDAO extends CrudDAO<Student_Program> {
    Student_Program getStudentProgramById(String value);
}
