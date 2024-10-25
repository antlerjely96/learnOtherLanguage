package managemark.managemark.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;

import java.sql.*;

import static java.lang.Integer.parseInt;

public class Subject {
    static Connection connection = null;
    private final SimpleIntegerProperty subjectId;
    private final SimpleStringProperty subjectName;
    private final SimpleIntegerProperty subjectDuration;

    public Subject(SimpleIntegerProperty subjectId, SimpleStringProperty subjectName, SimpleIntegerProperty subjectDuration) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDuration = subjectDuration;
    }

    public Subject(int subjectId, String subjectName, int subjectDuration){
        this.subjectId = new SimpleIntegerProperty(subjectId);
        this.subjectName = new SimpleStringProperty(subjectName);
        this.subjectDuration = new SimpleIntegerProperty(subjectDuration);
    }

    public int getSubjectId() {
        return subjectId.get();
    }

    public SimpleIntegerProperty subjectIdProperty() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId.set(subjectId);
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public SimpleStringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public int getSubjectDuration() {
        return subjectDuration.get();
    }

    public SimpleIntegerProperty subjectDurationProperty() {
        return subjectDuration;
    }

    public void setSubjectDuration(int subjectDuration) {
        this.subjectDuration.set(subjectDuration);
    }

    @Override
    public String toString() {
        return subjectName.get();
    }

    public static void create(TextField addNameField, TextField addDurationField) throws SQLException {
        String nameSubject = addNameField.getText();
        int durationSubject = parseInt(addDurationField.getText());
        String sql = "INSERT INTO subjects(name, duration) VALUES (?, ?)";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nameSubject);
        statement.setInt(2, durationSubject);
        statement.execute();
        DatabaseConnection.closeConnection(connection);
    }

    public static void update(TextField editIdField, TextField editNameField, TextField editDurationField) throws SQLException {
        String subjectId = editIdField.getText();
        String subjectName = editNameField.getText();
        int subjectDuration = parseInt(editDurationField.getText());
        connection = DatabaseConnection.getConnection();
        String sql = "UPDATE subjects SET name = ?, duration = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, subjectName);
        statement.setInt(2, subjectDuration);
        statement.setString(3, subjectId);
        statement.execute();
        DatabaseConnection.closeConnection(connection);
    }

    public static void destroy(Subject subject) throws SQLException {
        int subjectId = subject.getSubjectId();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM subjects WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, subjectId);
        statement.execute();
        DatabaseConnection.closeConnection(connection);
    }

    public static ResultSet getSubject() throws SQLException {
        String sql = "SELECT * FROM subjects";
        connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
