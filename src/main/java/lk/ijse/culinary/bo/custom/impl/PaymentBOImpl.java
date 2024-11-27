package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.PaymentBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.PaymentDAO;
import lk.ijse.culinary.dao.custom.ProgramDAO;
import lk.ijse.culinary.dto.PaymentDto;
import lk.ijse.culinary.dto.ProgramDto;
import lk.ijse.culinary.entity.Payment;
import lk.ijse.culinary.entity.Program;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean savePayment(PaymentDto dto) {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDto dto) {
        return false;
    }

    @Override
    public boolean deletePayment(String id) {
        return false;
    }

    @Override
    public List<PaymentDto> getAllPayment() {
        return null;
    }

    @Override
    public boolean isPaymentExist(PaymentDto dto) {
        return false;
    }

    @Override
    public Payment searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }


}
