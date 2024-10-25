package managemark.managemark.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;

import java.sql.*;

public class Major {

    static Connection connection = null;
    private final SimpleIntegerProperty majorId;
    private final SimpleStringProperty majorName;

    public Major(SimpleIntegerProperty majorId, SimpleStringProperty majorName){
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public Major(int majorId, String majorName){
        this.majorId = new SimpleIntegerProperty(majorId);
        this.majorName = new SimpleStringProperty(majorName);
    }

    public int getMajorId() {
        return majorId.get();
    }

    public SimpleIntegerProperty majorIdProperty() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId.set(majorId);
    }

    public String getMajorName() {
        return majorName.get();
    }

    public SimpleStringProperty majorNameProperty() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName.set(majorName);
    }

    @Override
    public String toString() {
        return majorName.get();
    }

    public static void create(TextField addNameField) throws SQLException {
        String majorName = addNameField.getText();
        String sql = "INSERT INTO majors(name) VALUES (?)";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, majorName);
        statement.execute();
    }

    public static void update(TextField editIdField, TextField editNameField) throws SQLException {
        String majorId = editIdField.getText();
        String majorName = editNameField.getText();
        connection = DatabaseConnection.getConnection();
        String sql = "UPDATE majors SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, majorName);
        statement.setString(2, majorId);
        statement.execute();
    }

    public static void destroy(Major major) throws SQLException {
        int majorId = major.getMajorId();
        String sql = "DELETE FROM majors WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, majorId);
        statement.execute();
        DatabaseConnection.closeConnection(connection);
    }

    public static ResultSet getMajor() throws SQLException {
        String sql = "SELECT * FROM majors";
        connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
