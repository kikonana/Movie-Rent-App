package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + config.DATABASE_NAME;
    private static final String DATABASE_USER = config.DATABASE_USER;
    private static final String DATABASE_PASSWORD = config.DATABASE_PASSWORD;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection."); 
            ex.printStackTrace();
        }
        return connection;
    }
}
