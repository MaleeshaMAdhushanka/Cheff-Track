package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.AdminDAO;
import lk.ijse.culinary.entity.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {


    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
    @Override
    public List<Admin> getAll() {
        String hql = "FROM Admin";
        Query<Admin> query = session.createQuery(hql, Admin.class);
        return query.list();
    }

    @Override
    public void save(Admin entity) {
      session.save(entity);


    }

    @Override
    public void update(Admin entity) {
       session.update(entity);


    }

    @Override
    public void delete(Admin entity) {
        session.delete(entity);

    }

    @Override
    public Admin search(String id) {
        return session.get(Admin.class, id);
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
    public int updateAdminUsername(String userName, String oldUsername) {
        String hql ="UPDATE Admin set username = :username WHERE username = :oldUsername";
        Query query = session.createQuery(hql);
        query.setParameter("username", userName);
        query.setParameter("oldUsername", oldUsername);
        int result = query.executeUpdate();
        return result;
    }
}

