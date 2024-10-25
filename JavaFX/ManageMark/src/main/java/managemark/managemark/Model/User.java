package managemark.managemark.Model;

import managemark.managemark.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    static Connection connection = null;

    public static ResultSet checkLogin(String email, String password) throws SQLException {
        connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        return statement.executeQuery();
    }
}
