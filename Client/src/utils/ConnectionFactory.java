package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;

public class ConnectionFactory {

    private ConnectionFactory() { }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(Config.URI, Config.USERNAME, Config.PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}

class Config {
    public static String URI = "jdbc:mysql://localhost:3306/library";
    public static String USERNAME = "library";
    public static String PASSWORD = "123123";
}
