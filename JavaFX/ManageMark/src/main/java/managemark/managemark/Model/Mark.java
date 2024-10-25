package managemark.managemark.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;

import java.sql.*;

public class Mark {

    static Connection connection = null;
    private SimpleIntegerProperty studentId;

    private SimpleIntegerProperty subjectId;

    private SimpleDoubleProperty mark;

    private SimpleIntegerProperty classId;

    private SimpleStringProperty studentName;

    private SimpleStringProperty subjectName;

   private SimpleStringProperty studentCode;

    public Mark(SimpleIntegerProperty studentId, SimpleIntegerProperty subjectId, SimpleDoubleProperty mark, SimpleIntegerProperty classId, SimpleStringProperty studentName, SimpleStringProperty subjectName, SimpleStringProperty studentCode) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.mark = mark;
        this.classId = classId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.studentCode = studentCode;
    }

    public Mark(int studentId, int subjectId, double mark, int classId, String studentName, String subjectName, String studentCode) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.subjectId = new SimpleIntegerProperty(subjectId);
        this.mark = new SimpleDoubleProperty(mark);
        this.classId = new SimpleIntegerProperty(classId);
        this.studentName = new SimpleStringProperty(studentName);
        this.subjectName = new SimpleStringProperty(subjectName);
        this.studentCode = new SimpleStringProperty(studentCode);
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

    public int getSubjectId() {
        return subjectId.get();
    }

    public SimpleIntegerProperty subjectIdProperty() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId.set(subjectId);
    }

    public double getMark() {
        return mark.get();
    }

    public SimpleDoubleProperty markProperty() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark.set(mark);
    }

    public int getClassId() {
        return classId.get();
    }

    public SimpleIntegerProperty classIdProperty() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId.set(classId);
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

    public String getSubjectName() {
        return subjectName.get();
    }

    public SimpleStringProperty subjectNameProperty() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
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

    public static ResultSet getMark(int subjectId, int classId) throws SQLException {
        connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM students LEFT JOIN marks" +
                " ON students.id = marks.student_id AND marks.subject_id = " + subjectId +
                " WHERE students.class_id = " + classId;
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public static void changeMark(Mark mark, TextField editStudentMarkField, ComboBox<Subject> addSubjectField) throws SQLException {
        int studentId = mark.getStudentId();
        int subjectId = addSubjectField.getValue().getSubjectId();
        double studentMark = Double.parseDouble(editStudentMarkField.getText());
        String sql = "REPLACE INTO marks VALUES (?, ?, ?)";
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, studentId);
        statement.setInt(2, subjectId);
        statement.setDouble(3, studentMark);
        statement.execute();
    }
}
