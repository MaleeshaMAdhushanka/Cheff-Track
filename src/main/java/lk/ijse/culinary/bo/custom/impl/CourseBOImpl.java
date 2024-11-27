package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.CourseBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.CourseDAO;
import lk.ijse.culinary.dto.CourseDto;
import lk.ijse.culinary.entity.Course;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    private Session session;

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean saveCourse(CourseDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            courseDAO.setSession(session);
            courseDAO.save(new Course(dto.getCourseID(), dto.getCourseName(), dto.getCourseDuration(), dto.getCourseFee()));
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
    public boolean updateCourse(CourseDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            courseDAO.setSession(session);
            courseDAO.update(new Course(dto.getCourseID(), dto.getCourseName(), dto.getCourseDuration(), dto.getCourseFee()));
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
    public boolean deleteCourse(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            courseDAO.setSession(session);
            Course course = courseDAO.search(id);
            if (course != null) {
                courseDAO.delete(course);
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
    public List<CourseDto> getAllCourse() {
        session = SessionFactoryConfig.getInstance().getSession();
        courseDAO.setSession(session);
        List<Course> courses = courseDAO.getAll();
        session.close();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            courseDtos.add(new CourseDto(course.getCourseID(), course.getCourseName(), course.getCourseDuration(), course.getCourseFee()));
        }
        return courseDtos;
    }

    @Override
    public boolean isCourseExist(CourseDto dto) {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return courseDAO.generateNextId();
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Course searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Course searchByName(String courseName) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getAllCourseIDs() {
        session = SessionFactoryConfig.getInstance().getSession();
        courseDAO.setSession(session);
        List<String> courseIDs = courseDAO.getAllCourseIDs();
        session.close();
        return courseIDs;
    }

    @Override
    public int getCourseCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        courseDAO.setSession(session);
        int count = courseDAO.getCourseCount();
        session.close();
        return count;
    }
}