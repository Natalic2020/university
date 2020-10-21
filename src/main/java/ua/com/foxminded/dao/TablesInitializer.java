package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final String URL = "jdbc:postgresql://localhost/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    public void createDB() {
        Arrays.stream(file.readFileToLines("sql_db.script")).forEach(this::dropCreateDB);

    }

    public void dropCreateDB(String sql) {
        logger.info("Remove and create DB University");
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.info(e.getMessage());
                }
            }
        }
    }

    public void createTables() {
        jdbcTemplate.batchUpdate(file.readFileToLines("sql.script"));
    }

    public void fillTables() {
        jdbcTemplate.batchUpdate(file.readFileToLines("tables.script"));
    }

    public void fillTablesNew() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Arrays.stream(file.readFileToLines("tablesHim.script")).forEach(query ->
            {
                session.createNativeQuery(query).executeUpdate();
            });

        tx1.commit();
        session.close();

    }

}
