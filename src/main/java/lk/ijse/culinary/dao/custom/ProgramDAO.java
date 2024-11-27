package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.Program;

public interface ProgramDAO extends CrudDAO<Program> {
    Program searchByProgramName(String programName) throws Exception;
}
