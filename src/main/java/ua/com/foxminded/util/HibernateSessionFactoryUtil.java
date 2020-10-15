package ua.com.foxminded.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;


import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/university")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "1234")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
                .setProperty("hibernate.connection.datasource", "java:com/driver/db/entity")
                .setProperty("hibernate.connection.password", "1234")
                .setProperty("show_sql", "true");
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Person.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    
   
   
    
}
