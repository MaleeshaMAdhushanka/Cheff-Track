package lk.ijse.culinary.bo.custom;

import lk.ijse.culinary.bo.SuperBO;
import lk.ijse.culinary.dto.StudentProgramDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentProgramBO extends SuperBO {
    public boolean saveStudentProgram(StudentProgramDto dto) throws Exception;

    public boolean StudentProgram(StudentProgramDto dto) throws Exception;

    public boolean StudentProgram(String ID)throws Exception;

    List<StudentProgramDto> StudentProgram() throws SQLException, ClassNotFoundException;

    public String generateNextId() throws SQLException, ClassNotFoundException;
}
