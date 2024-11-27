package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.StudentCourseBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.StudentCourseDAO;
import lk.ijse.culinary.dto.StudentCourseDto;
import lk.ijse.culinary.entity.StudentCourse;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudenetCourseBOImpl implements StudentCourseBO {
    private Session session;
    StudentCourseDAO studentcourseDAO = (StudentCourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT_COURSE);



    @Override
    public boolean saveStudenetCourse(StudentCourseDto dto) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentcourseDAO.setSession(session);
            studentcourseDAO.save(new StudentCourse(dto.getStudentCourseID(), dto.getRegistrationDate(), dto.getStudent(), dto.getCourse()));
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
    public boolean updateStudenetCourse(StudentCourseDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteStudenetCourse(StudentCourseDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean StudenetCourse(StudentCourseDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean StudenetCourse(String ID) throws Exception {
        return false;
    }

    @Override
    public List<StudentCourseDto> StudenetCourse() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<StudentCourse> getStudentCourseList() throws IOException {
        return null;
    }

    @Override
    public List<StudentCourseDto> getStudentCourses() {
        return null;
    }
}
