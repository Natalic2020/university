package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.util.FileParser;

@Repository
@Qualifier("tablesInitializer")
public class TablesInitializer {

    private static final String URL = "jdbc:postgresql://localhost";
    private static final String URL_UNI = URL + ":5432/university";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    public void createDB() {
        FileParser file = new FileParser();
        Arrays.stream(file.readFileToLines("sql_db.script")).forEach(this::dropCreateDB);
        Arrays.stream(file.readFileToLines("schema.script")).forEach(this::doQuery);
    }

    public void dropCreateDB(String sql) {
        logger.info("Remove and create DB University");
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL + "/", USERNAME, PASSWORD);

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
    
    public void doQuery(String sql) {
        logger.info("Remove and create Schema");
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL_UNI, USERNAME, PASSWORD);

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
}
