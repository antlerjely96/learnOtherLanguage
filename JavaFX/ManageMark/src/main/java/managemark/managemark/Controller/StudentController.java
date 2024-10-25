package managemark.managemark.Controller;
//import classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import managemark.managemark.AlertMessage;
import managemark.managemark.Model.ClassStudent;
import managemark.managemark.Model.Major;
import managemark.managemark.Model.SchoolYear;
import managemark.managemark.Model.Student;
import java.sql.*;

//Controller class for managing student.
public class StudentController {
    // Declare the TableView for displaying student data
    @FXML
    private TableView<Student> studentTable;
    // Define TableColumn for student ID
    @FXML
    private TableColumn<Student, Integer> studentIdColumn;
    // Define TableColumn for student code
    @FXML
    private TableColumn<Student, String> studentCodeColumn;
    // Define TableColumn for student name
    @FXML
    private TableColumn<Student, String> studentNameColumn;
    // Define TableColumn for student phone number
    @FXML
    private TableColumn<Student, String> studentPhoneColumn;
    // Define TableColumn for student email
    @FXML
    private TableColumn<Student, String> studentEmailColumn;
    // Define TableColumn for student class name
    @FXML
    private TableColumn<Student, String> studentClassNameColumn;
    // Define TableColumn for student class ID
    @FXML
    private TableColumn<Student, Integer> studentClassIdColumn;
    // Define TextField for adding student code
    @FXML
    public TextField addCodeField;
    // Define TextField for adding student name
    @FXML
    public TextField addNameField;
    // Define TextField for adding student email
    @FXML
    public TextField addEmailField;
    // Define TextField for adding student phone number
    @FXML
    public TextField addPhoneField;
    // Define ComboBox for selecting a major when adding a student
    @FXML
    public ComboBox<Major> addMajorField = new ComboBox<>();
    // Define ComboBox for selecting a school year when adding a student
    @FXML
    public ComboBox<SchoolYear> addSchoolYearField = new ComboBox<>();
    // Define ComboBox for selecting a class when adding a student
    @FXML
    public ComboBox<ClassStudent> addClassField = new ComboBox<>();
    // TableColumn for the edit functionality
    private TableColumn<Student, Void> editColumn = new TableColumn<>("Edit");
    // Define TextField for editing student ID
    @FXML
    public TextField editIdField;
    // Define TextField for editing student code
    @FXML
    public TextField editCodeField;
    // Define TextField for editing student name
    @FXML
    public TextField editNameField;
    // Define TextField for editing student email
    @FXML
    public TextField editEmailField;
    // Define TextField for editing student phone number
    @FXML
    public TextField editPhoneField;
    // Define ComboBox for selecting a class when editing a student
    @FXML
    public ComboBox<ClassStudent> editClassField = new ComboBox<>();
    // TableColumn for the delete functionality
    private TableColumn<Student, Void> deleteColumn = new TableColumn<>("Delete");

    // Initialize method that populates student data in the TableView
    public void initialize(){
        try {
            // Retrieve a list of students
            ObservableList<Student> students = getStudent();
            // Set the items in the TableView to the list of students
            studentTable.setItems(students);
            // Define cell value factories for each TableColumn to display student data
            studentIdColumn.setCellValueFactory(cellDate -> cellDate.getValue().studentIdProperty().asObject());
            studentCodeColumn.setCellValueFactory(cellData -> cellData.getValue().studentCodeProperty());
            studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
            studentEmailColumn.setCellValueFactory(cellData -> cellData.getValue().studentEmailProperty());
            studentPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().studentPhoneProperty());
            studentClassIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentClassIdProperty().asObject());
            studentClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentClassNameProperty());
            // Define cell factory for the Edit column
            Callback<TableColumn<Student, Void>, TableCell<Student, Void>> editCellFactory = new Callback<>() {
                @Override
                public TableCell<Student, Void> call(TableColumn<Student, Void> studentVoidTableColumn) {
                    //Create a TableCell for Student objects with a custom layout.
                    TableCell<Student, Void> cell = new TableCell<>(){
                        // Initialize a Button for editing
                        private Button editButton = new Button("Edit");
                        {
                            // Define action for the Edit button
                            editButton.setOnAction(actionEvent -> {
                                // Retrieve the selected student
                                Student student = getTableView().getItems().get(getIndex());
                                //show the edit dialog
                                showDialogEditStudent(student);
                            });
                        }
                        @Override
                        public void updateItem(Void item, boolean empty) {
                            // Call the super method to update the item
                            super.updateItem(item, empty);
                            // Check if the cell is empty
                            if (empty) {
                                // If the cell is empty, do not display any graphic
                                setGraphic(null);
                            } else {
                                // If the cell is not empty, display the editButton
                                setGraphic(editButton);
                            }
                        }
                    };
                    return cell;
                }
            };
            // Set the cell factory for the Edit column
            editColumn.setCellFactory(editCellFactory);
            // Add the Edit column to the TableView
            studentTable.getColumns().add(editColumn);
            // Define cell factory for the Delete column
            Callback<TableColumn<Student, Void>, TableCell<Student, Void>> deleteCellFactory = new Callback<>() {
                @Override
                public TableCell<Student, Void> call(TableColumn<Student, Void> studentVoidTableColumn) {
                    //Create a TableCell for Student objects with a custom layout.
                    TableCell<Student, Void> cell = new TableCell<>(){
                        // Initialize a Button for deleting
                        private Button deleteButton = new Button("Delete");
                        {
                            // Define action for the Delete button
                            deleteButton.setOnAction(actionEvent -> {
                                // Retrieve the selected student
                                Student student = getTableView().getItems().get(getIndex());
                                try {
                                    //delete selected student
                                    Student.destroy(student);
                                    //show a success message
                                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Deleted!", "Deleted student");
                                    //reload the table
                                    reloadTableView();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                        @Override
                        public void updateItem(Void item, boolean empty) {
                            // Call the super method to update the item
                            super.updateItem(item, empty);
                            // Check if the cell is empty
                            if (empty) {
                                // If the cell is empty, do not display any graphic
                                setGraphic(null);
                            } else {
                                // If the cell is not empty, display the deleteButton
                                setGraphic(deleteButton);
                            }
                        }
                    };
                    return cell;
                }
            };
            // Set the cell factory for the Delete column
            deleteColumn.setCellFactory(deleteCellFactory);
            // Add the Delete column to the TableView
            studentTable.getColumns().add(deleteColumn);
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve a list of students
    public ObservableList<Student> getStudent(){
        // Initialize an empty ObservableList to store the retrieved Student objects
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            // Retrieve student data from the database
            ResultSet results = Student.getStudent();
            // Iterate over the result set and create Student objects
            while (results.next()){
                // Extract student data from the result set
                int studentId = results.getInt("id");
                String studentCode = results.getString("code");
                String studentName = results.getString("name");
                String studentEmail = results.getString("email");
                String studentPhone = results.getString("phone");
                int studentClassId = results.getInt("class_id");
                String studentClassName = results.getString("class_name");
                // Create a new Student object
                Student student = new Student(studentId, studentCode, studentName, studentEmail, studentPhone, studentClassId, studentClassName);
                // Add the Student object to the list
                students.add(student);
            }
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
        // Return the list of students
        return students;
    }

    // Method to show a dialog for creating a new student
    public void showDialogCreateStudent() {
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set title of the stage
        stage.setTitle("Add student");
        try {
            // Load the FXML file containing the form for adding a student
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/students/student-add.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve form fields from the loader's namespace
            addCodeField = (TextField) loader.getNamespace().get("addCodeField");
            addNameField = (TextField) loader.getNamespace().get("addNameField");
            addEmailField = (TextField) loader.getNamespace().get("addEmailField");
            addPhoneField = (TextField) loader.getNamespace().get("addPhoneField");
            addMajorField = (ComboBox<Major>) loader.getNamespace().get("addMajorField");
            addSchoolYearField = (ComboBox<SchoolYear>) loader.getNamespace().get("addSchoolYearField");
            addClassField = (ComboBox<ClassStudent>) loader.getNamespace().get("addClassField");
            // Populate the major and school year fields
            getMajor();
            getSchoolYears();
            // Add listener for changes in the major field
            addMajorField.valueProperty().addListener((observable, oldValue, newValue) -> {
                if(addSchoolYearField.getValue() != null){
                    getClassStudentAdd(newValue.getMajorId(), addSchoolYearField.getValue().getSchoolYearId());
                } else {
                    getClassStudentAdd(newValue.getMajorId(), 0);
                }
            });
            // Add listener for changes in the school year field
            addSchoolYearField.valueProperty().addListener((observable, oldValue, newValue) -> {
                if(addMajorField.getValue() != null){
                    getClassStudentAdd(addMajorField.getValue().getMajorId(), newValue.getSchoolYearId());
                } else {
                    getClassStudentAdd(0, newValue.getSchoolYearId());
                }
            });
            // Define action for the create button
            Button createButton = (Button) loader.getNamespace().get("createButton");
            createButton.setOnAction(actionEvent -> {
                try {
                    // Create a new student with the provided information
                    Student.create(addCodeField, addNameField, addEmailField, addPhoneField, addClassField);
                    // Show a success message
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Created!", "Created student");
                    // close the dialog
                    stage.close();
                    // reload the table
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and display the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to reload the data in the table view with updated student information
    private void reloadTableView() {
        // Retrieve the updated list of students
        ObservableList<Student> newItem = getStudent();
        // Set the table view items to the new list of students
        studentTable.setItems(newItem);
    }

    // Method to retrieve and populate the major field in the form
    private void getMajor(){
        try {
            // Retrieve majors from the database
            ResultSet result = Major.getMajor();
            // Iterate over the result set and create Major objects
            while(result.next()){
                // Extract major data from the result set
                int majorId = result.getInt("id");
                String majorName = result.getString("name");
                // Create a new Major object
                Major major = new Major(majorId, majorName);
                // Add the Major object to the major field's items
                addMajorField.getItems().add(major);
            }
        } catch (Exception e) {
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve and populate the school years field in the form
    private void getSchoolYears(){
        try {
            // Retrieve school years from the database
            ResultSet result = SchoolYear.getSchoolYear();
            // Iterate over the result set and create SchoolYear objects
            while (result.next()) {
                // Extract school year data from the result set
                int schoolYearId = result.getInt("id");
                String schoolYearName = result.getString("name");
                int schoolYearStart = result.getInt("start_year");
                int schoolYearEnd = result.getInt("end_year");
                // Create a new SchoolYear object
                SchoolYear schoolYear = new SchoolYear(schoolYearId, schoolYearName, schoolYearStart, schoolYearEnd);
                // Add the SchoolYear object to the school year field's items
                addSchoolYearField.getItems().add(schoolYear);
            }
        } catch (Exception e) {
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve and populate class options based on selected major and school year
    private void getClassStudentAdd(int selectedMajor, int selectedSchoolYear){
        try {
            // Clear existing items in the class field
            addClassField.getItems().clear();
            // Retrieve class student data based on selected major and school year
            ResultSet results = Student.getClassStudentAdd(selectedMajor, selectedSchoolYear);
            // Populate the class field based on the retrieved results
            getClassByMajorAndSchoolYear(results, addClassField);
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to show a dialog for editing a student's information
    public void showDialogEditStudent(Student student){
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set title of the stage
        stage.setTitle("Edit student");
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            // Load the FXML file containing the form for editing a student
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/students/student-edit.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve form fields from the loader's namespace
            editIdField = (TextField) loader.getNamespace().get("editIdField");
            editNameField = (TextField) loader.getNamespace().get("editNameField");
            editCodeField = (TextField) loader.getNamespace().get("editCodeField");
            editEmailField = (TextField) loader.getNamespace().get("editEmailField");
            editPhoneField = (TextField) loader.getNamespace().get("editPhoneField");
            editClassField = (ComboBox<ClassStudent>) loader.getNamespace().get("editClassField");
            // Populate form fields with the student's information
            getClassStudentEdit(student);
            editIdField.setText(String.valueOf(student.getStudentId()));
            editCodeField.setText(String.valueOf(student.getStudentCode()));
            editNameField.setText(student.getStudentName());
            editEmailField.setText(student.getStudentEmail());
            editPhoneField.setText(student.getStudentPhone());
            editClassField.setValue(new ClassStudent(student.getStudentClassId(), student.getStudentClassName(), 0, "", 0, "", 0, 0));
            // Define action for the update button
            Button updateButton = (Button) loader.getNamespace().get("updateButton");
            updateButton.setOnAction(actionEvent -> {
                try {
                    // Update the student's information with the provided data
                    Student.update(editIdField, editNameField, editPhoneField, editClassField);
                    // Show a success message
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated student");
                    // close the dialog
                    stage.close();
                    // reload the table
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and display the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve and populate class options for editing based on the student's class
    private void getClassStudentEdit(Student student) throws SQLException {
        // Retrieve the class ID of the student
        int classId = student.getStudentClassId();
        // Retrieve major and school year information based on the class ID
        ResultSet resultsMajorAndSchoolYear = Student.getMajorAndSchoolYear(classId);
        // Iterate over the major and school year results to fetch class options
        while (resultsMajorAndSchoolYear.next()){
            // Extract major ID and school year ID
            int majorId = resultsMajorAndSchoolYear.getInt("major_id");
            int schoolYearId = resultsMajorAndSchoolYear.getInt("school_year_id");
            // Retrieve class options based on major ID and school year ID
            ResultSet resultClass = ClassStudent.getClassStudent(majorId, schoolYearId);
            // Populate the class field with options based on the retrieved class result set
            getClassByMajorAndSchoolYear(resultClass, editClassField);
        }
    }

    // Method to populate class options in the editClassField based on major and school year
    private void getClassByMajorAndSchoolYear(ResultSet resultClass, ComboBox<ClassStudent> editClassField) throws SQLException {
        // Iterate over the result set of class options
        while (resultClass.next()){
            // Extract class information from the result set
            int resultClassId = resultClass.getInt("id");
            String resultClassName = resultClass.getString("name");
            int resultMajorId = resultClass.getInt("major_id");
            int resultSchoolYearId = resultClass.getInt("school_year_id");
            // Create a new ClassStudent object with the extracted information
            ClassStudent classStudent = new ClassStudent(resultClassId, resultClassName, resultMajorId, "", resultSchoolYearId, "", 0, 0);
            // Add the classStudent object to the items in the editClassField
            editClassField.getItems().add(classStudent);
        }
    }
}
