package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.Admin;

public interface AdminDAO extends CrudDAO<Admin> {

    int updateAdminUsername(String userName,String oldUsername);

}
