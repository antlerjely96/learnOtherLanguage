package managemark.managemark.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;

import java.sql.*;

import static java.lang.Integer.parseInt;

public class SchoolYear {
    static Connection connection = null;
    private final SimpleIntegerProperty schoolYearId;

    private final SimpleStringProperty schoolYearName;

    private final SimpleIntegerProperty schoolYearStart;

    private final SimpleIntegerProperty schoolYearEnd;

    public SchoolYear(SimpleIntegerProperty schoolYearId, SimpleStringProperty schoolYearName, SimpleIntegerProperty schoolYearStart, SimpleIntegerProperty schoolYearEnd) {
        this.schoolYearId = schoolYearId;
        this.schoolYearName = schoolYearName;
        this.schoolYearStart = schoolYearStart;
        this.schoolYearEnd = schoolYearEnd;
    }

    public SchoolYear(int schoolYearId, String schoolYearName, int schoolYearStart, int schoolYearEnd) {
        this.schoolYearId = new SimpleIntegerProperty(schoolYearId);
        this.schoolYearName = new SimpleStringProperty(schoolYearName);
        this.schoolYearStart = new SimpleIntegerProperty(schoolYearStart);
        this.schoolYearEnd = new SimpleIntegerProperty(schoolYearEnd);
    }

    public int getSchoolYearId() {
        return schoolYearId.get();
    }

    public SimpleIntegerProperty schoolYearIdProperty() {
        return schoolYearId;
    }

    public void setSchoolYearId(int schoolYearId) {
        this.schoolYearId.set(schoolYearId);
    }

    public String getSchoolYearName() {
        return schoolYearName.get();
    }

    public SimpleStringProperty schoolYearNameProperty() {
        return schoolYearName;
    }

    public void setSchoolYearName(String schoolYearName) {
        this.schoolYearName.set(schoolYearName);
    }

    public int getSchoolYearStart() {
        return schoolYearStart.get();
    }

    public SimpleIntegerProperty schoolYearStartProperty() {
        return schoolYearStart;
    }

    public void setSchoolYearStart(int schoolYearStart) {
        this.schoolYearStart.set(schoolYearStart);
    }

    public int getSchoolYearEnd() {
        return schoolYearEnd.get();
    }

    public SimpleIntegerProperty schoolYearEndProperty() {
        return schoolYearEnd;
    }

    public void setSchoolYearEnd(int schoolYearEnd) {
        this.schoolYearEnd.set(schoolYearEnd);
    }

    @Override
    public String toString() {
        return schoolYearName.get();
    }

    public static void create(TextField addNameField, TextField addStartField, TextField addEndField) throws SQLException {
        String schoolYearName = addNameField.getText();
        int schoolYearStart = parseInt(addStartField.getText());
        int schoolYearEnd = parseInt(addEndField.getText());
        String sql = "INSERT INTO school_years(name, start_year, end_year) VALUES (?, ?, ?)";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, schoolYearName);
        statement.setInt(2, schoolYearStart);
        statement.setInt(3, schoolYearEnd);
        statement.execute();
        DatabaseConnection.closeConnection(connection);
    }

    public static void update(TextField editIdField, TextField editNameField, TextField editStartField, TextField editEndField) throws SQLException {
        int schoolYearId = Integer.parseInt(editIdField.getText());
        String schoolYearName = editNameField.getText();
        int schoolYearStart = Integer.parseInt(editStartField.getText());
        int schoolYearEnd = Integer.parseInt(editEndField.getText());
        String sql = "UPDATE school_years SET name = ?, start_year = ?, end_year = ? WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, schoolYearName);
        statement.setInt(2, schoolYearStart);
        statement.setInt(3, schoolYearEnd);
        statement.setInt(4, schoolYearId);
        statement.execute();
    }

    public static void destroy(SchoolYear schoolYear) throws SQLException {
        int schoolYearId = schoolYear.getSchoolYearId();
        String sql = "DELETE FROM school_years WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, schoolYearId);
        statement.execute();
    }

    public static ResultSet getSchoolYear() throws SQLException {
        connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM school_years";
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
