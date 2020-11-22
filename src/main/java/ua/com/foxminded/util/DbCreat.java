package ua.com.foxminded.util;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbCreat {
    static final String JDBC_DRIVER = "org.postgresql.Driver";  
    static final String DB_URL = "jdbc:postgresql://localhost/";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "1234";
    
    static Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try{
       
       Class.forName(JDBC_DRIVER);
       logger.info("Connecting to database...");
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       logger.info("Creating database...");
       stmt = conn.createStatement();
       
       String sql = "DROP DATABASE IF EXISTS university";
       String sqlCreate = "CREATE DATABASE university";
       stmt.executeUpdate(sql);
       stmt.executeUpdate(sqlCreate);
       logger.info("Database created successfully...");
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
    logger.info("Goodbye!");
 }
 }

