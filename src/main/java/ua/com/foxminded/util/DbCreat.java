package ua.com.foxminded.util;

import java.sql.*;

public class DbCreat {
    static final String JDBC_DRIVER = "org.postgresql.Driver";  
    static final String DB_URL = "jdbc:postgresql://localhost/";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "1234";
    
    public static void main(String[] args) {
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
       //Handle errors for JDBC
       se.printStackTrace();
    }catch(Exception e){
       //Handle errors for Class.forName
       e.printStackTrace();
    }finally{
       //finally block used to close resources
       try{
          if(stmt!=null)
             stmt.close();
       }catch(SQLException se2){
       }// nothing we can do
       try{
          if(conn!=null)
             conn.close();
       }catch(SQLException se){
          se.printStackTrace();
       }//end finally try
    }//end try
    System.out.println("Goodbye!");
 }//end main
 }

