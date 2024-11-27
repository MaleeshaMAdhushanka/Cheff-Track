package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.StudentBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.StudentDAO;
import lk.ijse.culinary.dto.StudentCourseDto;
import lk.ijse.culinary.dto.StudentDto;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private Session session;
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDto dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            Student student = new Student(
                    dto.getId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getDob(),
                    dto.getEmail(),
                    dto.getContact(),
                    dto.getCourse()
            );
            studentDAO.save(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateStudent(StudentDto dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            Student student = new Student(
                    dto.getId(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getDob(),
                    dto.getEmail(),
                    dto.getContact(),
                    dto.getCourse()
            );
            studentDAO.update(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteStudent(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentDAO.setSession(session);
            Student student = studentDAO.search(id);
            if (student != null) {
                studentDAO.delete(student);
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
    public List<StudentDto> getAllStudent() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<Student> students = studentDAO.getAll();
        session.close();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            studentDtos.add(new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getDob(),
                    student.getEmail(),
                    student.getContact(),
                    student.getCourse()
            ));
        }
        return studentDtos;
    }

    @Override
    public boolean isStudentExist(StudentDto dto) {
        return false;
    }

    @Override
    public String getNewStudentId(String id) throws Exception {
        return studentDAO.generateNextId();
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Student searchByContact(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    // New methods
    @Override
    public List<String> getAllStudentEmails() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<String> studentEmails = studentDAO.getAllStudentEmails();
        session.close();
        return studentEmails;
    }

    @Override
    public List<String> getAllCourseIDs() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<String> courseIDs = studentDAO.getAllCourseIDs();
        session.close();
        return courseIDs;
    }

    @Override
    public int getStudentCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        int count = studentDAO.getStudentCount();
        session.close();
        return count;
    }

    @Override
    public int getRegisteredStudentCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        int count = studentDAO.getRegisteredStudentCount();
        session.close();
        return count;
    }

    @Override
    public List<StudentCourseDto> getAllStudentCourses() {
        session = SessionFactoryConfig.getInstance().getSession();
        studentDAO.setSession(session);
        List<StudentCourseDto> studentCourses = studentDAO.getAllStudentCourses();
        session.close();
        return studentCourses;
    }
}