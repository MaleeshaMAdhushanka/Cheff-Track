// PaymentBOImpl.java
package lk.ijse.culinary.bo.custom.impl;

import lk.ijse.culinary.bo.custom.PaymentBO;
import lk.ijse.culinary.dao.DAOFactory;
import lk.ijse.culinary.dao.custom.PaymentDAO;
import lk.ijse.culinary.dto.PaymentDto;
import lk.ijse.culinary.entity.Payment;
import lk.ijse.culinary.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private Session session;
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean savePayment(PaymentDto dto) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                paymentDAO.setSession(session);
                List<Payment> existingPayments = paymentDAO.searchByStudentAndCourse(dto.getStudentEmail(), dto.getCourseID());
                if (!existingPayments.isEmpty()) {
                    Payment existingPayment = existingPayments.get(0);
                    double newBalance = existingPayment.getBalanceAmount() - dto.getUpfrontAmount();
                    existingPayment.setBalanceAmount(newBalance);
                    existingPayment.setUpfrontAmount(existingPayment.getUpfrontAmount() + dto.getUpfrontAmount());
                    paymentDAO.update(existingPayment);
                } else {
                    String nextId = paymentDAO.generateNextId();
                    Payment payment = new Payment(
                            nextId,
                            dto.getPaymentDate().toLocalDate(),
                            dto.getPayamount(),
                            dto.getStatus(),
                            dto.getUpfrontAmount(),
                            dto.getBalanceAmount(),
                            dto.getCourseID(),
                            dto.getStudentEmail(),
                            null
                    );
                    paymentDAO.save(payment);
                }
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean updatePayment(PaymentDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            paymentDAO.setSession(session);
            paymentDAO.update(new Payment(
                    dto.getPaymentID(),
                    dto.getPaymentDate().toLocalDate(),
                    dto.getPayamount(),
                    dto.getStatus(),
                    dto.getUpfrontAmount(),
                    dto.getBalanceAmount(),
                    dto.getCourseID(),
                    dto.getStudentEmail(),
                    dto.getStudentcourse()
            ));
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
    public boolean deletePayment(String id) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            paymentDAO.setSession(session);
            Payment payment = paymentDAO.search(id);
            if (payment != null) {
                paymentDAO.delete(payment);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<PaymentDto> getAllPayment() {
        session = SessionFactoryConfig.getInstance().getSession();
        paymentDAO.setSession(session);
        List<Payment> payments = paymentDAO.getAll();
        session.close();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getPaymentID(),
                    java.sql.Date.valueOf(payment.getPaymentDate()),
                    payment.getPayamount(),
                    payment.getStatus(),
                    payment.getUpfrontAmount(),
                    payment.getBalanceAmount(),
                    payment.getCourseID(),
                    payment.getStudentEmail(),
                    payment.getStudentCourse()
            ));
        }
        return paymentDtos;
    }

    @Override
    public boolean isPaymentExist(PaymentDto dto) {
        return false;
    }

    @Override
    public Payment searchById(String id) {
        return paymentDAO.search(id);
    }

    @Override
    public String generateNextId() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        paymentDAO.setSession(session);
        return paymentDAO.generateNextId();
    }
}