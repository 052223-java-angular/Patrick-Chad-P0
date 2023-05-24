package com.revature.YourStore.Utils;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private static Connection connection;

    private ConnectionFactory() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
    }

    /* ------- Helper Methods ------- */
    private Properties getProperties(){
        Properties props = new Properties();

        return props;
    }
}
