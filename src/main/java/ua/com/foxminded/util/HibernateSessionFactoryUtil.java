package ua.com.foxminded.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import ua.com.foxminded.dao.entity.ContactInfo;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Period;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.entity.TimeSlot;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    static Logger logger = LoggerFactory.getLogger("SampleLogger");
    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Room.class)
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Subject.class)
                .addAnnotatedClass(TimeSlot.class)
                .addAnnotatedClass(Period.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(ContactInfo.class)
                .addAnnotatedClass(ScheduleItem.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return sessionFactory;
    }
    
   
   
    
}
