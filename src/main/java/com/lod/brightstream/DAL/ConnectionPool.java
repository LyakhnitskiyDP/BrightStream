package com.lod.brightstream.DAL;


import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionPool {
    
    //DataSource is a new alternative to DriverManager.
    private static final BasicDataSource ds = new BasicDataSource();
    
    //Initialization 
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException classException) {
            System.err.println(classException);
        }
        ds.setUrl("jdbc:mysql://192.168.0.11:3308/brightstream?useUnicode=true&serverTimezone=UTC");
        ds.setUsername("");
        ds.setPassword("");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    
    public static Connection getConnection() {
        
        try {
            return ds.getConnection();
        } catch (SQLException sqlException) {
            
            sqlException.printStackTrace();
            throw new RuntimeException("Connection cannot be established");
        }
        
    }
    
    public static void freeConnection(Connection connection) {
        
        ds.invalidateConnection(connection);
    }
    
    private ConnectionPool(){ }
    
    
}
