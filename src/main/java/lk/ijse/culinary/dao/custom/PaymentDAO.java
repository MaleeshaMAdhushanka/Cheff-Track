package lk.ijse.culinary.dao.custom;

import lk.ijse.culinary.dao.CrudDAO;
import lk.ijse.culinary.entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment> {
    String getCurrentId() throws Exception;
}
