package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.StudentDAO;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Cannot set a null or closed session.");
        }
        this.session = session;
    }

    @Override
    public List<Student> getAll() {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Session is not available or already closed.");
        }
        String hql = "FROM Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        return query.list();
    }

    @Override
    public void save(Student entity) {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Session is not available or already closed.");
        }
        session.save(entity);
    }

    @Override
    public void update(Student entity) {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Session is not available or already closed.");
        }
        session.update(entity);
    }

    @Override
    public void delete(Student entity) {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Session is not available or already closed.");
        }
        session.delete(entity);
    }

    @Override
    public Student search(String id) {
        if (session == null || !session.isOpen()) {
            throw new IllegalStateException("Session is not available or already closed.");
        }
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
    public Student searchStudentByContact(String id) {
        return null;
    }

    @Override
    public List<Student> searchByContact2(String id) {
        return null;
    }

}
