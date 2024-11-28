package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.StudentDAO;
import lk.ijse.culinary.dto.StudentCourseDto;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public List<String> getAllStudentEmails() {
        String hql = "SELECT email FROM Student";
        Query<String> query = session.createQuery(hql, String.class);
        return query.list();
    }

    @Override
    public List<String> getAllCourseIDs() {
        String hql = "SELECT courseID FROM Course";
        Query<String> query = session.createQuery(hql, String.class);
        return query.list();
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Student> getAll() {
        String hql = "FROM Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        return query.list();
    }

    @Override
    public void save(Student entity) {
        session.save(entity);
    }

    @Override
    public void update(Student entity) {
        session.update(entity);
    }

    @Override
    public void delete(Student entity) {
        session.delete(entity);
    }

    @Override
    public Student search(String id) {
        return session.get(Student.class, id);
    }

    @Override
    public String generateNextId() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT id FROM Student ORDER BY id DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();

                if (itemCode.startsWith("S") && itemCode.length() > 1) {
                    int idNum = Integer.parseInt(itemCode.substring(1));
                    nextId = "S" + String.format("%03d", ++idNum);
                } else {
                    nextId = "S001";
                }
            } else {
                nextId = "S001";
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nextId;
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Student searchByContact(String id) {
        return null;
    }

    @Override
    public List<Student> searchByContact2(String id) {
        return null;
    }

    @Override
    public Student findByEmail(String studentEmail) {
        String hql = "FROM Student WHERE email = :email";
        return session.createQuery(hql, Student.class)
                .setParameter("email", studentEmail)
                .uniqueResult();
    }

    @Override
    public int getStudentCount() {
//        String hql = "SELECT COUNT(*) FROM Student";
//        Query<Long> query = session.createQuery(hql, Long.class);
//        return query.uniqueResult().intValue();
        return session.createQuery("SELECT COUNT(*) FROM Student", Long.class).uniqueResult().intValue();
    }

    @Override
    public int getRegisteredStudentCount() {
//        String hql = "SELECT COUNT(*) FROM Student WHERE id = true";
//        Query<Long> query = session.createQuery(hql, Long.class);
//        return query.uniqueResult().intValue();
        return session.createQuery("SELECT COUNT(*) FROM Student WHERE id = true", Long.class).uniqueResult().intValue();
    }

    @Override
    public List<StudentCourseDto> getAllStudentCourses() {
        String hql = "SELECT new lk.ijse.culinary.dto.StudentCourseDto(s.id, s.name, c.courseName, c.duration, sc.registeredDate, sc.restPayAmount) " +
                "FROM StudentCourse sc JOIN sc.student s JOIN sc.course c";
        Query<StudentCourseDto> query = session.createQuery(hql, StudentCourseDto.class);
        return query.list();
    }
}