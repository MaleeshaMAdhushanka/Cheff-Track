package lk.ijse.culinary.dao;

import lk.ijse.culinary.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum  DAOTypes {
        User, ADMIN, STUDENT, PROGRAM, PAYMENT, STUDENT_PROGRAM
    }

    public StudentDAOImpl getDAO(DAOTypes types) {
        switch (types){
            case User:
                return new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT_PROGRAM:
                return new StudentProgramDAOImpl();
            default:
                return null;
        }
    }

