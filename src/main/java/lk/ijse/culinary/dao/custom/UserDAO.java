package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.User;

public interface UserDAO extends CrudDAO<User> {
    int updateUserEmail(String email,String oldEmail);
}
