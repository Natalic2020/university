package ua.com.foxminded.config;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.com.foxminded.dao.entity.Room;

public class App {

    
    public static void main(String[] args) {
    System.out.println("Hello world");

    Room nata = new Room();
    nata.setId(UUID.randomUUID().toString());
   
    
    Configuration configuration =  new Configuration().configure().addAnnotatedClass(Room.class);
    
//  ServiceRegistry reg = new ServiceRegistryBuilder
        
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//    sessionFactory = configuration.buildSessionFactory(builder.build());
    
    
    SessionFactory sessionFactory =  configuration.buildSessionFactory(builder.build());
    
    Session session = sessionFactory.openSession();
    
    Transaction tx = session.beginTransaction() ;
    
    session.save(nata);
    
    tx.commit();
    }
}
