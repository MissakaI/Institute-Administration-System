/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author midda
 */
public class ConnectionFactory {
    private Connection connection;
    private static ConnectionFactory dBConnection;
    
    private ConnectionFactory(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File dbFile=new File("settings/dbSettings.properties");
        Properties dbProperties = new Properties();
        FileReader dbReader;
        
        try {
            dbReader=new FileReader(dbFile);
            dbProperties.load(dbReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String dbSettings=String.format("//%s/%s", dbProperties.getProperty("ip"),dbProperties.getProperty("database"));
        
        
        
        
        try {
            //connection=DriverManager.getConnection("jdbc:mysql://localhost/sathra", dbProperties.getProperty("username"), dbProperties.getProperty("password"));
            connection=DriverManager.getConnection("jdbc:mysql://localhost/sathra", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionFactory getInstance(){
        if (dBConnection==null){
            dBConnection=new ConnectionFactory();
        }
        return dBConnection;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
