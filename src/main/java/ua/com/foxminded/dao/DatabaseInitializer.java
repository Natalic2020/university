package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.foxminded.util.FileParser;

@Component
public class DatabaseInitializer {

    private static final String URL = "jdbc:postgresql://localhost/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public void createDB() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql_db.script");
        sqlQueryList.forEach(this::runSQL);

    }

    public void runSQL(String sql) {
        Connection connection = null;

        Statement statement;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
