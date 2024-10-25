package managemark.managemark.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;

import java.sql.*;

public class Student {
    static Connection connection = null;
    private final SimpleIntegerProperty studentId;
    private final SimpleStringProperty studentCode;
    private final SimpleStringProperty studentName;
    private final SimpleStringProperty studentEmail;
    private final SimpleStringProperty studentPhone;
    private final SimpleIntegerProperty studentClassId;
    private final SimpleStringProperty studentClassName;

    public Student(SimpleIntegerProperty studentId, SimpleStringProperty studentCode, SimpleStringProperty studentName, SimpleStringProperty studentEmail, SimpleStringProperty studentPhone, SimpleIntegerProperty studentClassId, SimpleStringProperty studentClassName) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentClassId = studentClassId;
        this.studentClassName = studentClassName;
    }

    public Student(int studentId, String studentCode, String studentName, String studentEmail, String studentPhone, int studentClassId, String studentClassName) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.studentCode = new SimpleStringProperty(studentCode);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentEmail = new SimpleStringProperty(studentEmail);
        this.studentPhone = new SimpleStringProperty(studentPhone);
        this.studentClassId = new SimpleIntegerProperty(studentClassId);
        this.studentClassName = new SimpleStringProperty(studentClassName);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public SimpleIntegerProperty studentIdProperty() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getStudentCode() {
        return studentCode.get();
    }

    public SimpleStringProperty studentCodeProperty() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode.set(studentCode);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public String getStudentEmail() {
        return studentEmail.get();
    }

    public SimpleStringProperty studentEmailProperty() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail.set(studentEmail);
    }

    public String getStudentPhone() {
        return studentPhone.get();
    }

    public SimpleStringProperty studentPhoneProperty() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone.set(studentPhone);
    }

    public int getStudentClassId() {
        return studentClassId.get();
    }

    public SimpleIntegerProperty studentClassIdProperty() {
        return studentClassId;
    }

    public void setStudentClassId(int studentClassId) {
        this.studentClassId.set(studentClassId);
    }

    public String getStudentClassName() {
        return studentClassName.get();
    }

    public SimpleStringProperty studentClassNameProperty() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName.set(studentClassName);
    }

    @Override
    public String toString() {
        return studentName.get();
    }

    public static ResultSet getStudent() throws SQLException {
        connection = DatabaseConnection.getConnection();
        String sql = "SELECT students.*, classes.name AS class_name FROM students INNER JOIN classes ON classes.id = students.class_id";
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void create(TextField addCodeField, TextField addNameField, TextField addEmailField, TextField addPhoneField, ComboBox<ClassStudent> addClassField) throws SQLException {
        String studentCode = addCodeField.getText();
        String studentName = addNameField.getText();
        String studentEmail = addEmailField.getText();
        String studentPhone = addPhoneField.getText();
        int studentClass = addClassField.getValue().getClassId();
        String sql = "INSERT INTO students(code, name, email, phone, class_id) VALUES (?, ?, ?, ?, ?)";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, studentCode);
        statement.setString(2, studentName);
        statement.setString(3, studentEmail);
        statement.setString(4, studentPhone);
        statement.setInt(5, studentClass);
        statement.execute();
    }

    public static void update(TextField editIdField, TextField editNameField, TextField editPhoneField, ComboBox<ClassStudent> editClassField) throws SQLException {
        int studentId = Integer.parseInt(editIdField.getText());
        String studentName = editNameField.getText();
        String studentPhone = editPhoneField.getText();
        int studentClassId = editClassField.getValue().getClassId();
        String sql = "UPDATE students SET name = ?, phone = ?, class_id = ? WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, studentName);
        statement.setString(2, studentPhone);
        statement.setInt(3, studentClassId);
        statement.setInt(4, studentId);
        statement.execute();
    }

    public static void destroy(Student student) throws SQLException {
        int studentId = student.getStudentId();
        String sql = "DELETE FROM students WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, studentId);
        statement.execute();
    }

    public static ResultSet getClassStudentAdd(int selectedMajor, int selectedSchoolYear) throws SQLException {
        String sql = "";
        connection = DatabaseConnection.getConnection();
        if(selectedMajor == 0){
            sql = "SELECT * FROM classes WHERE school_year = " + selectedSchoolYear;
        } else if (selectedSchoolYear == 0) {
            sql = "SELECT * FROM classes WHERE major_id = " + selectedMajor;
        } else {
            sql = "SELECT * FROM classes WHERE major_id = " + selectedMajor + " AND school_year_id = " + selectedSchoolYear;
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static ResultSet getMajorAndSchoolYear(int id) throws SQLException {
        String sql = "SELECT major_id, school_year_id FROM classes WHERE id = ?";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement.executeQuery();
    }
}
