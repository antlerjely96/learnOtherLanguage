package managemark.managemark.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import managemark.managemark.DatabaseConnection;
import java.sql.*;
import static java.lang.Integer.parseInt;

public class ClassStudent {
    // Static variable to store the database connection
    static Connection connection = null;
    // SimpleIntegerProperty for storing the class ID
    private final SimpleIntegerProperty classId;
    // SimpleIntegerProperty for storing the major ID
    private final SimpleIntegerProperty majorId;
    // SimpleIntegerProperty for storing the school year ID
    private final SimpleIntegerProperty schoolYearId;
    // SimpleStringProperty for storing the class name
    private final SimpleStringProperty className;
    // SimpleStringProperty for storing the major name
    private final SimpleStringProperty majorName;
    // SimpleStringProperty for storing the school year name
    private final SimpleStringProperty schoolYearName;
    // SimpleIntegerProperty for storing the start year of the school year
    private final SimpleIntegerProperty schoolYearStart;
    // SimpleIntegerProperty for storing the end year of the school year
    private final SimpleIntegerProperty schoolYearEnd;

    //Constructor
    public ClassStudent(SimpleIntegerProperty classId, SimpleIntegerProperty majorId, SimpleIntegerProperty schoolYearId, SimpleStringProperty className, SimpleStringProperty majorName, SimpleStringProperty schoolYearName, SimpleIntegerProperty schoolYearStart, SimpleIntegerProperty schoolYearEnd) {
        this.classId = classId;
        this.majorId = majorId;
        this.schoolYearId = schoolYearId;
        this.className = className;
        this.majorName = majorName;
        this.schoolYearName = schoolYearName;
        this.schoolYearStart = schoolYearStart;
        this.schoolYearEnd = schoolYearEnd;
    }

    //Constructor
    public ClassStudent(int classId, String className, int majorId, String majorName, int schoolYearId, String schoolYearName, int schoolYearStart, int schoolYearEnd) {
        this.classId = new SimpleIntegerProperty(classId);
        this.majorId = new SimpleIntegerProperty(majorId);
        this.schoolYearId = new SimpleIntegerProperty(schoolYearId);
        this.className = new SimpleStringProperty(className);
        this.majorName = new SimpleStringProperty(majorName);
        this.schoolYearName = new SimpleStringProperty(schoolYearName);
        this.schoolYearStart = new SimpleIntegerProperty(schoolYearStart);
        this.schoolYearEnd = new SimpleIntegerProperty(schoolYearEnd);
    }

    // Getter method for retrieving the class ID
    public int getClassId() {
        return classId.get();
    }

    // Property accessor method for the class ID
    public SimpleIntegerProperty classIdProperty() {
        return classId;
    }

    // Setter method for setting the class ID
    public void setClassId(int classId) {
        this.classId.set(classId);
    }

    // Getter method for retrieving the major ID
    public int getMajorId() {
        return majorId.get();
    }

    // Property accessor method for the major ID
    public SimpleIntegerProperty majorIdProperty() {
        return majorId;
    }

    // Setter method for setting the major ID
    public void setMajorId(int majorId) {
        this.majorId.set(majorId);
    }

    // Getter method for retrieving the school year ID
    public int getSchoolYearId() {
        return schoolYearId.get();
    }

    // Property accessor method for the school year ID
    public SimpleIntegerProperty schoolYearIdProperty() {
        return schoolYearId;
    }

    // Setter method for setting the school year ID
    public void setSchoolYearId(int schoolYearId) {
        this.schoolYearId.set(schoolYearId);
    }

    // Getter method for retrieving the class name
    public String getClassName() {
        return className.get();
    }

    // Property accessor method for the class name
    public SimpleStringProperty classNameProperty() {
        return className;
    }

    // Setter method for setting the class name
    public void setClassName(String className) {
        this.className.set(className);
    }

    // Getter method for retrieving the major name
    public String getMajorName() {
        return majorName.get();
    }

    // Property accessor method for the major name
    public SimpleStringProperty majorNameProperty() {
        return majorName;
    }

    // Setter method for setting the major name
    public void setMajorName(String majorName) {
        this.majorName.set(majorName);
    }

    // Getter method for retrieving the school year name
    public String getSchoolYearName() {
        return schoolYearName.get();
    }

    // Property accessor method for the school year name
    public SimpleStringProperty schoolYearNameProperty() {
        return schoolYearName;
    }

    // Setter method for setting the school year name
    public void setSchoolYearName(String schoolYearName) {
        this.schoolYearName.set(schoolYearName);
    }

    // Getter method for retrieving the start year of the school year
    public int getSchoolYearStart() {
        return schoolYearStart.get();
    }

    // Property accessor method for the start year of the school year
    public SimpleIntegerProperty schoolYearStartProperty() {
        return schoolYearStart;
    }

    // Setter method for setting the start year of the school year
    public void setSchoolYearStart(int schoolYearStart) {
        this.schoolYearStart.set(schoolYearStart);
    }

    // Getter method for retrieving the end year of the school year
    public int getSchoolYearEnd() {
        return schoolYearEnd.get();
    }

    // Property accessor method for the end year of the school year
    public SimpleIntegerProperty schoolYearEndProperty() {
        return schoolYearEnd;
    }

    // Setter method for setting the end year of the school year
    public void setSchoolYearEnd(int schoolYearEnd) {
        this.schoolYearEnd.set(schoolYearEnd);
    }

    // Override the toString method to return the class name as a string representation
    @Override
    public String toString() {
        return className.get();
    }

    // Method to retrieve a result set of students belonging to a specific class based on major ID and school year ID
    public static ResultSet getClassStudent(int majorId, int schoolYearId) throws SQLException {
        String sql = "";
        // Construct SQL query based on the provided major ID and school year ID
        if(majorId == 0 && schoolYearId == 0){
            sql = "SELECT classes.*, majors.name AS major_name, school_years.name AS school_year_name, school_years.start_year, school_years.end_year FROM classes " +
                    "INNER JOIN majors ON classes.major_id = majors.id " +
                    "INNER JOIN school_years ON classes.school_year_id = school_years.id";
        } else {
            sql = "SELECT classes.*, majors.name AS major_name, school_years.name AS school_year_name, school_years.start_year, school_years.end_year FROM classes " +
                    "INNER JOIN majors ON classes.major_id = majors.id " +
                    "INNER JOIN school_years ON classes.school_year_id = school_years.id WHERE classes.major_id = " + majorId +
                    " AND school_year_id = " + schoolYearId;
        }
        // Establish a database connection and create a statement
        connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        // Execute the SQL query and return the result set
        return statement.executeQuery(sql);
    }

    // Method to create a new class in the database based on the input fields
    public static void create(TextField addNameField, ComboBox<Major> addMajorField, ComboBox<SchoolYear> addSchoolYearField) throws SQLException {
        // Retrieve values from input fields
        String nameClass = addNameField.getText();
        int majorId = addMajorField.getValue().getMajorId();
        int schoolYearId = addSchoolYearField.getValue().getSchoolYearId();
        // SQL query to insert a new class
        String sql = "INSERT INTO classes(name, major_id, school_year_id) VALUES (?, ?, ?)";
        // Establish a database connection and prepare a statement
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        // Set the parameter in the prepared statement
        statement.setString(1, nameClass);
        statement.setInt(2, majorId);
        statement.setInt(3, schoolYearId);
        // Execute the insert statement
        statement.execute();
    }

    // Method to update an existing class in the database based on the input fields
    public static void update(TextField editIdField, TextField editNameField, ComboBox<Major> editMajorField, ComboBox<SchoolYear> editSchoolYearField) throws SQLException {
        // Retrieve values from input fields
        int classId = parseInt(editIdField.getText());
        String className = editNameField.getText();
        int majorId = editMajorField.getValue().getMajorId();
        int schoolYearId = editSchoolYearField.getValue().getSchoolYearId();
        // Establish a database connection
        Connection connection = DatabaseConnection.getConnection();
        // SQL query to update a class
        String sql = "UPDATE classes SET name = ?, major_id = ?, school_year_id = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        // Set the parameter in the prepared statement
        statement.setString(1, className);
        statement.setInt(2, majorId);
        statement.setInt(3, schoolYearId);
        statement.setInt(4, classId);
        // Execute the update statement
        statement.execute();
    }

    // Method to delete a class from the database
    public static void destroy(ClassStudent classStudent) throws SQLException {
        // Retrieve the class ID
        int classId = classStudent.getClassId();
        // Establish a database connection
        Connection connection = DatabaseConnection.getConnection();
        // SQL query to delete a class
        String sql = "DELETE FROM classes WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        // Set the parameter in the prepared statement
        statement.setInt(1, classId);
        // Execute the delete statement
        statement.execute();
    }
}
