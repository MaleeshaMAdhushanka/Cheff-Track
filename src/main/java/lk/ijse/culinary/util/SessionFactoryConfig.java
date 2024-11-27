package lk.ijse.culinary.util;


//import lk.ijse.culinary.entity.*;
import lk.ijse.culinary.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {


    private static  SessionFactoryConfig factoryConfiguration;

    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Program.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Student_Program.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static  SessionFactoryConfig getInstance(){
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }
























//    private static SessionFactoryConfig factoryConfig;
//
//    private SessionFactoryConfig(){}
//
//
//    public static  SessionFactoryConfig getInstance(){
//        return (factoryConfig == null) ?
//                factoryConfig = new SessionFactoryConfig(): factoryConfig;
//    }
//    public Session getSession(){
//
//        //Creating service registry
//
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .loadProperties("hibernate.properties")
//                .build();
//
//
//        //Creatine meta data and build it
//
//        Metadata metadata = new MetadataSources(serviceRegistry)
//
//                .addAnnotatedClass(Admin.class)
//                .addAnnotatedClass(Payment.class)
//                .addAnnotatedClass(Program.class)
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Student_Program.class)
//                .addAnnotatedClass(User.class)
//                .getMetadataBuilder().build();
//
//
////        Creting Session Factory
//
//      SessionFactory sessionFactory =  metadata.buildSessionFactory();
//
//      return sessionFactory.openSession();
//
//
//
//    }



}
