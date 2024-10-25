package managemark.managemark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //config connection database
    private final static String url = "jdbc:mysql://localhost:3306/mark_manage";
    private final static String username = "root";
    private final static String password = "";

    //Method to connect database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    //Method to close connect
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
