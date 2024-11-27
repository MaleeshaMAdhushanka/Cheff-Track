// PaymentDAOImpl.java
package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.PaymentDAO;
import lk.ijse.culinary.entity.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(Payment entity) {
        session.save(entity);
    }

    @Override
    public void update(Payment entity) {
        session.update(entity);
    }

    @Override
    public void delete(Payment entity) {
        session.delete(entity);
    }

    @Override
    public Payment search(String id) {
        return session.get(Payment.class, id);
    }

    @Override
    public List<Payment> getAll() {
        return session.createQuery("FROM Payment", Payment.class).list();
    }

    @Override
    public String generateNextId() {
        String hql = "SELECT p.payment_ID FROM Payment p ORDER BY p.payment_ID DESC";
        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);
        String lastId = query.uniqueResult();
        if (lastId != null) {
            int nextId = Integer.parseInt(lastId.substring(1)) + 1;
            return "P" + String.format("%03d", nextId);
        } else {
            return "P001";
        }
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public String getCurrentId() throws Exception {
        return null;
    }

    @Override
    public List<Payment> searchByStudentAndCourse(String studentEmail, String courseID) {
        String hql = "FROM Payment WHERE studentEmail = :studentEmail AND courseID = :courseID";
        Query<Payment> query = session.createQuery(hql, Payment.class);
        query.setParameter("studentEmail", studentEmail);
        query.setParameter("courseID", courseID);
        return query.list();
    }
}