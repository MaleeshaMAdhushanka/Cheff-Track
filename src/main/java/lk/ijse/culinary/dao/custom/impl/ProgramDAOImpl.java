package lk.ijse.culinary.dao.custom.impl;

import lk.ijse.culinary.dao.custom.ProgramDAO;
import lk.ijse.culinary.entity.Program;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Program> getAll() {
      session = SessionFactoryConfig.getInstance().getSession();
      Transaction transaction = session.beginTransaction();

        try {
            String hql = "FROM Program ";
            List<Program> courses = session.createQuery(hql, Program.class).list();
            transaction.commit();
            return courses;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Program entity) {
        session.save(entity);
    }

    @Override
    public void update(Program entity) {
        session.update(entity);
    }

    @Override
    public void delete(Program entity) {
        session.delete(entity);
    }

    @Override
    public Program search(String id) {
       return session.get(Program.class, id) ;
    }

    @Override
    public String generateNextId() {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT programId FROM Course ORDER BY programId DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();


                if (itemCode.startsWith("PA") && itemCode.length() > 2) {

                    int idNum = Integer.parseInt(itemCode.substring(2));
                    nextId = "PA" + String.format("%04d", ++idNum);
                } else {

                    nextId = "PA1001";
                }
            } else {
                nextId = "PA1001";
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
    public Program searchByProgramName(String programName) throws Exception {
        return null;
    }


}
