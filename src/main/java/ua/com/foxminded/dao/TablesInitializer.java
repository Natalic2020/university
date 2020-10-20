package ua.com.foxminded.dao;




import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.util.FileParser;
import ua.com.foxminded.util.HibernateSessionFactoryUtil;

@Repository
@Qualifier("tablesInitializer")
public class TablesInitializer {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    FileParser file;
    
    public void createTables() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql.script"));
    }  
    
    public void fillTables() {
        jdbcTemplate.batchUpdate( file.readFileToLines("tables.script"));
    }

    public void fillTablesNew() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        
        Arrays.stream(file.readFileToLines("tablesHim.script")).forEach(query -> {
        session.createNativeQuery(query).executeUpdate(); 
        });
        
//        session.createNativeQuery( "INSERT INTO uni.rooms (id_room, name_room ) values ('026621cc-73a6-40ba-8ea7-86628f4cb802', 'room 1')" )
//                  .executeUpdate();
//        
//        session.createNativeQuery( "INSERT INTO uni.persons (id_person, first_name, last_name) values ('f17e5b3a-5963-4098-a2ff-26b497701e70', 'Nata', 'Svitlychna')" )
//                 .executeUpdate();
//
//        session.createNativeQuery( "INSERT INTO uni.groups (id_group, name_group ) values ('0c149265-57c0-4942-a1e5-06c8b6983a23', 'gr-1')" )
//        .executeUpdate();
//        
//        session.createNativeQuery( "INSERT INTO uni.students (id_student, id_person, study_status, start_of_study, citizenship , grants , id_group ) values ('a17f83c5-a85a-4420-8423-23b86d0463c6', 'f17e5b3a-5963-4098-a2ff-26b497701e70', 'ACCEPTED', '2019-01-01', 'Ukraine', 100, '0c149265-57c0-4942-a1e5-06c8b6983a23')" )
//        .executeUpdate();
        
        tx1.commit();
        session.close();
        
    }  
    
}
