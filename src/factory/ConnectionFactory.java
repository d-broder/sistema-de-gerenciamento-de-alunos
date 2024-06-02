package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Properties prop = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("res/config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties file", e);
        }
    }

    public static Connection getConnection() {
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
