package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.CourseDAO;
import lk.ijse.culinary.entity.Course;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Course> getAll() {
        String hql = "FROM Course";
        Query<Course> query = session.createQuery(hql, Course.class);
        return query.list();
    }

    @Override
    public void save(Course entity) {
        session.save(entity);
    }

    @Override
    public void update(Course entity) {
        session.update(entity);
    }

    @Override
    public void delete(Course entity) {
        session.delete(entity);
    }

    @Override
    public Course search(String id) {
        return session.get(Course.class, id);
    }

    @Override
    public String generateNextId() {
        try {
            session = SessionFactoryConfig.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            String hql = "SELECT c.id FROM Course c ORDER BY c.id DESC";
            String lastId = session.createQuery(hql, String.class).setMaxResults(1).uniqueResult();

            transaction.commit();
            session.close();

            if (lastId != null) {
                int nextId = Integer.parseInt(lastId.replace("CA", "")) + 1;
                return String.format("CA%04d", nextId);
            } else {
                return "CA1001";
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
            }
            throw new RuntimeException("Failed to generate next course ID", e);
        }
    }


    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Course searchByCourseName(String courseName) throws Exception {
        return null;
    }

    @Override
    public List<String> getAllCourseIDs() {
        String hql = "SELECT courseID FROM Course";
        Query<String> query = session.createQuery(hql, String.class);
        return query.list();
    }

    @Override
    public int getCourseCount() {
        String hql = "SELECT COUNT(*) FROM Course";
        Query<Long> query = session.createQuery(hql, Long.class);
        return query.uniqueResult().intValue();
    }
}