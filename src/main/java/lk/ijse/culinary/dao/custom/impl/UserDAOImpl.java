package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.UserDAO;
import lk.ijse.culinary.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAll() {
        String hql = "FROM User";
        Query<User> query = session.createQuery(hql, User.class);
        return query.list();
    }

    @Override
    public void save(User entity) {
        session.save(entity);


    }

    @Override
    public void update(User entity)  {
       session.update(entity);


    }

    @Override
    public void delete(User entity)  {
        session.delete(entity);

    }

    @Override
    public User search(String id) {
        return session.get(User.class, id);
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
    public List<User> getUsersWithOverdueBooks() {
        String hql = "SELECT u FROM User u JOIN u.bookTransactions t WHERE t.returnDate < CURRENT_DATE AND t.isReturned = false";
        Query query = session.createQuery(hql);
        List<User> users = query.list();
        return users;
    }

    @Override
    public int updateUserEmail(String email, String oldEmail) {
        String hql ="UPDATE User set email = :email WHERE email = :oldEmail";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("oldEmail", oldEmail);
        int result = query.executeUpdate();
        return result;
    }


}

