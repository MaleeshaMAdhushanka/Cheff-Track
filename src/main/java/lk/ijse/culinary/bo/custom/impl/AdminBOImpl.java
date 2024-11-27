package lk.ijse.culinary.bo.custom.impl;

import javafx.scene.shape.Circle;
import lk.ijse.culinary.bo.custom.AdminBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.AdminDAO;
import lk.ijse.culinary.dto.AdminDto;
import lk.ijse.culinary.entity.Admin;
import lk.ijse.culinary.util.PasswordEncrypt;
import lk.ijse.culinary.util.PasswordVerifier;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {


    public static Admin loggedAdmin;

    public static Circle circleImg;

    private Session session;

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);


    @Override
    public List<AdminDto> getAllAdmin() {
        session = SessionFactoryConfig.getInstance().getSession();
        adminDAO.setSession(session);
        List<AdminDto> adminList = new ArrayList<>();
        try {
            for (Admin admin : adminDAO.getAll()) {
                adminList.add(new AdminDto(
                        admin.getUsername(),
                        admin.getPassword()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return adminList;
    }


    @Override
    public boolean saveAdmin(AdminDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            adminDAO.setSession(session);
            String hashedPassword = PasswordEncrypt.hashPassword(dto.getPassword());
            adminDAO.save(new Admin(dto.getUsername(), hashedPassword, dto.getImgUrl()));
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
    public boolean updateAdmin(AdminDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            adminDAO.setSession(session);
            Admin admin = adminDAO.search(AdminBOImpl.loggedAdmin.getUsername());
            session.clear();
            if (!(admin.getUsername().equals(dto.getUsername()))) {
                int result = adminDAO.updateAdminUsername(dto.getUsername(), admin.getUsername());
                if (!(result > 0)) {
                    throw new RuntimeException("Something went wrong");
                }
            }
            adminDAO.update(new Admin(dto.getUsername(), dto.getPassword(), dto.getImgUrl()));
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
    public boolean deleteAdmin(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            adminDAO.setSession(session);
            Admin admin = adminDAO.search(id);
            adminDAO.delete(admin);
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
    public boolean isAdminExist(AdminDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        adminDAO.setSession(session);
        try {
            Admin search = adminDAO.search(dto.getUsername());
            if (search != null) {
                if (PasswordVerifier.verifyPassword(dto.getPassword(), search.getPassword())) {
                    loggedAdmin = search;
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;

    }
}