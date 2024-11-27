package lk.ijse.culinary.bo.custom;

import lk.ijse.culinary.bo.SuperBO;
import lk.ijse.culinary.dto.ProgramDto;
import lk.ijse.culinary.entity.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean saveProgram(ProgramDto dto);
    boolean updateProgram(ProgramDto dto);
    boolean deleteProgram(String id);
    List<ProgramDto> getAllProgram();
    boolean isProgramExist(ProgramDto dto);
    public String generateNextId() throws SQLException, ClassNotFoundException;
    List<String> getIds();

    public Program searchById(String id) throws SQLException, ClassNotFoundException;

    Program searchByName(String courseName) throws SQLException, ClassNotFoundException;
}
