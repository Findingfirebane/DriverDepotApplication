package app.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DatabaseConnection {
/**
 * singleton to enforce 1 active connection at a time
 * ensures thread saftey
 */
    INSTANCE();

    public static DatabaseConnection getInstance(){
        return INSTANCE;
    }

    public Connection getConnection()throws SQLException {
        Properties properties = new Properties();
/**
 * try with resources for autoclosing 
 */
        try{
            InputStream in = INSTANCE.getClass().getClassLoader().getResourceAsStream("database.properties");
            properties.load(in);
        }catch (IOException e){
            e.printStackTrace();
        }

        String url =properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection con = null;
        try{
        con = DriverManager.getConnection(url,username,password);
        System.out.printf("Connection details: %s  %s  %s" ,url, username, password );
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;


    }   
}
