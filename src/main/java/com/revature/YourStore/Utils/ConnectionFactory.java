package com.revature.YourStore.Utils;

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

        conn = DriverManager.getConnection(props.getProperty("url"),props.getProperty("user"), props.getProperty("password"));
    }

    /* ------- Methods ------- */

    public static ConnectionFactory getInstance() throws ClassNotFoundException, SQLException, IOException{
        if (instance == null || instance.getConnection().isClosed()){
            instance = new ConnectionFactory();
        }

        return instance;
    }

    private Connection getConnection(){
        return conn;
    }

    /* ------- Helper Methods ------- */
    private Properties getProperties() throws IOException{
        Properties props = new Properties();

        try(InputStream istream = getClass().getClassLoader().getResourceAsStream("application.properties")){
            if(istream == null){
                throw new IOException("Application.properties could not be found");
            }
        }

        return props;
    }
}
