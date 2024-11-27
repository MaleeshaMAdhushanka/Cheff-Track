package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<User> getUsersWithOverdueBooks();
    int updateUserEmail(String email,String oldEmail);
}
