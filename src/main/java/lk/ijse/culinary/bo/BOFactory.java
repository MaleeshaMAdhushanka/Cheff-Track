package lk.ijse.culinary.bo;

import lk.ijse.culinary.bo.custom.UserBO;
import lk.ijse.culinary.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        User, ADMIN, PROGRAM, STUDENT, PAYMENT, STUDENT_PROGRAM
    }
    public  SuperBO getBO(BOTypes botype) {

        switch (botype) {
            case User:
            return new UserBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
                case PAYMENT:
                    return new PaymentBOImpl();
                    case STUDENT_PROGRAM:
                        return new StudentProgramBOImpl();
                        default:
                            return null;
        }
    }

}
