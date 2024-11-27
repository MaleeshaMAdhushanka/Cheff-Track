package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.ProgramBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.ProgramDAO;
import lk.ijse.culinary.dto.ProgramDto;
import lk.ijse.culinary.entity.Program;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    private Session session;

    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean saveProgram(ProgramDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            programDAO.setSession(session);
            programDAO.save(new Program(dto.getProgramId(),dto.getProgramName(),dto.getProgramDuration(),dto.getProgramFee()));
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateProgram(ProgramDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            programDAO.setSession(session);
            programDAO.update(new Program(dto.getProgramId(), dto.getProgramName(), dto.getProgramDuration(), dto.getProgramFee()));
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteProgram(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            programDAO.setSession(session);
            Program program = ProgramDAO.search(id);
            if (program != null) {
                ProgramDAO.delete(program);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<ProgramDto> getAllCourse() {
        session = SessionFactoryConfig.getInstance().getSession();
        programDAO.setSession(session);
        List<Program> programs = programDAO.getAll();
        session.close();
        List<ProgramDto> programDtos = new ArrayList<>();
        for (Program program : programs) {
            programDtos.add(new ProgramDto(program.getProgramId(), program.getProgramName(), program.getProgramDuration(), program.getProgramFee()));
        }
        return programDtos;
    }

    @Override
    public boolean isCourseExist(ProgramDto dto) {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return  programDAO.generateNextId();
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Program searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Program searchByName(String programName) throws SQLException, ClassNotFoundException {
        return null;
    }



}
