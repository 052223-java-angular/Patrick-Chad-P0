package com.revature.ecommerce.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private static Connection conn;

    private ConnectionFactory() throws ClassNotFoundException, IOException, SQLException{
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }

    /* ------- Methods ------- */   

    public static ConnectionFactory getInstance() throws ClassNotFoundException, SQLException, IOException{
        if (instance == null || instance.getConnection().isClosed()){
            instance = new ConnectionFactory();
           //System.out.println("ConnectionFactory null. Instantiating new ConnectionFactory");
        }

        //System.out.println("Connection in ConnectionFactory:" + instance);
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }

    /* ------- Helper Methods ------- */
    private Properties getProperties() throws IOException{
        Properties props = new Properties();

        try(InputStream istream = getClass().getClassLoader().getResourceAsStream("application.properties")){
            if(istream == null){
                throw new IOException("Application.properties could not be found");
            }
            props.load(istream);
        }

        return props;
    }
}
