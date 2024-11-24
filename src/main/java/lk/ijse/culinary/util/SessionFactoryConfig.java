package lk.ijse.culinary.util;


//import lk.ijse.culinary.entity.*;
import lk.ijse.culinary.entity.Programs;
import lk.ijse.culinary.entity.Student;
import lk.ijse.culinary.entity.Student_Program;
import lk.ijse.culinary.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student_Program.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Package.class)
                .addAnnotatedClass(Programs.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }
    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
