package lk.ijse.culinary.dao;

import lk.ijse.culinary.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,ADMIN,STUDENT,COURSE,PAYMENT,STUDENT_COURSE
    }

    public SuperDAO  getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return  new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT_COURSE:
                return new StudentCourseDAOImpl();
            default:
                return null;
        }
    }

}

