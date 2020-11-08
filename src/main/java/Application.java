import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ua.com.foxminded.util.DbCreat;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@ComponentScan(basePackages = "ua.com.foxminded")
@EnableJpaRepositories(basePackages = "ua.com.foxminded.dao")
@EntityScan(basePackages = "ua.com.foxminded.dao.entity")
public class Application extends SpringBootServletInitializer{
    
    static final String JDBC_DRIVER = "org.postgresql.Driver";  
    static final String DB_URL = "jdbc:postgresql://localhost/";
    static final String USER = "postgres";
    static final String PASS = "1234";
    
    public static void main(String[] args) {     
        dbCreate();
        SpringApplication.run(Application.class, args);
    }
    
    
    public static void dbCreate () {
        Connection conn = null;
        Statement stmt = null;
        try{
           
           Class.forName(JDBC_DRIVER);
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           System.out.println("Creating database...");
           stmt = conn.createStatement();
           
           String sql = "DROP DATABASE IF EXISTS university";
           String sqlCreate = "CREATE DATABASE university";
           stmt.executeUpdate(sql);
           stmt.executeUpdate(sqlCreate);
           System.out.println("Database created successfully...");
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }
        }
        System.out.println("Goodbye!");
     }
    
}
