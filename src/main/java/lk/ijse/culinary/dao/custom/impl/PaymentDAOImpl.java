package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.PaymentDAO;
import lk.ijse.culinary.entity.Payment;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private Session session;


    @Override
    public List<Payment> getAll() {
        String hql = "FROM Payment";
        Query<Payment> query = session.createQuery(hql, Payment.class);
        return query.list();

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
    public String getCurrentId() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object payment = session.createQuery
                ("SELECT P.paymentID FROM Payment P  ORDER BY P.paymentID DESC LIMIT 1").uniqueResult();
        if (payment != null) {
            String userId = payment.toString();
            String prefix = "P";
            String[] split = userId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "P001";
        }
        transaction.commit();
        session.close();
        return nextId;
    }
}
