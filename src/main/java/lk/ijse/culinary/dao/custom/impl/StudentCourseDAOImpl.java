package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.StudentCourseDAO;
import lk.ijse.culinary.entity.StudentCourse;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentCourseDAOImpl implements StudentCourseDAO {
    private Session session;
    @Override
    public List<StudentCourse> getAll() {
        String hql = "FROM StudentCourse";
        Query<StudentCourse> query = session.createQuery(hql, StudentCourse.class);
        return query.list();
    }

    @Override
    public void save(StudentCourse entity) {
        session.save(entity);


    }

    @Override
    public void update(StudentCourse entity) {
        session.update(entity);


    }

    @Override
    public void delete(StudentCourse entity) {
        session.delete(entity);

    }

    @Override
    public StudentCourse search(String id) {
        return session.get(StudentCourse.class, id);
    }

    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public StudentCourse getStudentCourseById(String value) {
        return null;
    }
}
