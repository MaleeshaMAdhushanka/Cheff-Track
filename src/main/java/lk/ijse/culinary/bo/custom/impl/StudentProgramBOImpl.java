package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.StudentProgramBO;
import lk.ijse.culinary.dto.StudentProgramDto;

import java.sql.SQLException;
import java.util.List;

public class StudentProgramBOImpl implements StudentProgramBO {

    @Override
    public boolean saveStudentProgram(StudentProgramDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean StudentProgram(StudentProgramDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean StudentProgram(String ID) throws Exception {
        return false;
    }

    @Override
    public List<StudentProgramDto> StudentProgram() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
