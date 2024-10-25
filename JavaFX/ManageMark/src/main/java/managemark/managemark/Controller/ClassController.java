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
import java.sql.*;

//Controller class for managing class of student.
public class ClassController {
    //Define FXML annotation for the TableView representing class data.
    @FXML
    private TableView<ClassStudent> classTable;
    //Define FXML annotation for the TableColumn representing class IDs.
    @FXML
    private TableColumn<ClassStudent, Integer> classIdColumn;
    //Define FXML annotation for the TableColumn representing class names.
    @FXML
    private TableColumn<ClassStudent, String> classNameColumn;
    //Define FXML annotation for the TableColumn representing major IDs.
    @FXML
    private TableColumn<ClassStudent, Integer> majorIdColumn;
    //Define FXML annotation for the TableColumn representing major names.
    @FXML
    private TableColumn<ClassStudent, String> majorNameColumn;
    //Define FXML annotation for the TableColumn representing school year IDs.
    @FXML
    private TableColumn<ClassStudent, Integer> schoolYearIdColumn;
    //Define FXML annotation for the TableColumn representing school year names.
    @FXML
    private TableColumn<ClassStudent, String> schoolYearNameColumn;
    //Define FXML annotation for the TableColumn representing school year start years.
    @FXML
    private TableColumn<ClassStudent, Integer> schoolYearStartColumn;
    //Define FXML annotation for the TableColumn representing school year end years.
    @FXML
    private TableColumn<ClassStudent, Integer> schoolYearEndColumn;
    //Define FXML annotation for the TextField used for adding a class name.
    @FXML
    private TextField addNameField;
    //Define FXML annotation for the ComboBox used for adding a major.
    @FXML
    private ComboBox<Major> addMajorField = new ComboBox<>();
    //Define FXML annotation for the ComboBox used for adding a school year.
    @FXML
    private ComboBox<SchoolYear> addSchoolYearField = new ComboBox<>();
    //Define a TableColumn for editing class data.
    private TableColumn<ClassStudent, Void> editColumn = new TableColumn<>("Edit");
    //Define a TableColumn for deleting class data.
    private TableColumn<ClassStudent, Void> deleteColumn = new TableColumn<>("Delete");
    //Define FXML annotation for the TextField used for editing a class ID.
    @FXML
    private TextField editIdField;
    //Define FXML annotation for the TextField used for editing a class name.
    @FXML
    private TextField editNameField;
    //Define FXML annotation for the ComboBox used for editing a major.
    @FXML
    private ComboBox<Major> editMajorField = new ComboBox<>();
    //Define FXML annotation for the ComboBox used for editing a school year.
    @FXML
    private ComboBox<SchoolYear> editSchoolYearField = new ComboBox<>();

    //Initialize method called when the Controller is loaded.
    public void initialize() {
        //Get a list of ClassStudent objects
        ObservableList<ClassStudent> classes = getClassStudent();
        // Set the items in the TableView to the retrieved list of classes
        classTable.setItems(classes);
        // Set cell value factories for various columns to display class data
        classIdColumn.setCellValueFactory(cellData -> cellData.getValue().classIdProperty().asObject());
        classNameColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        majorIdColumn.setCellValueFactory(cellData -> cellData.getValue().majorIdProperty().asObject());
        majorNameColumn.setCellValueFactory(cellData -> cellData.getValue().majorNameProperty());
        schoolYearIdColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearIdProperty().asObject());
        schoolYearNameColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearNameProperty());
        schoolYearStartColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearIdProperty().asObject());
        schoolYearEndColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearIdProperty().asObject());
        // Callback for creating Edit button in a TableCell
        Callback<TableColumn<ClassStudent, Void>, TableCell<ClassStudent, Void>> cellEditFactory = new Callback<>() {
            @Override
            public TableCell<ClassStudent, Void> call(TableColumn<ClassStudent, Void> classStudentVoidTableColumn) {
                //Create a TableCell for ClassStudent objects with a custom layout.
                TableCell<ClassStudent, Void> cell = new TableCell<>(){
                    // Initialize a Button for editing
                    private final Button editButton = new Button("Edit");
                    {
                        // Event handler for the Edit button
                        editButton.setOnAction(actionEvent -> {
                            // Retrieve the ClassStudent object associated with this row
                            ClassStudent classStudent = getTableView().getItems().get(getIndex());
                            // Show a dialog for editing the class details
                            showDialogEditClass(classStudent);
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
        editColumn.setCellFactory(cellEditFactory);
        // Add the edit column to the classTable
        classTable.getColumns().add(editColumn);
        // Callback for creating Delete button in a TableCell
        Callback<TableColumn<ClassStudent, Void>, TableCell<ClassStudent, Void>> cellDeleteFactory = new Callback<>() {
            @Override
            public TableCell<ClassStudent, Void> call(TableColumn<ClassStudent, Void> classStudentVoidTableColumn) {
                //Create a TableCell for ClassStudent objects with a delete button.
                TableCell<ClassStudent, Void> cell = new TableCell<>(){
                    // Initialize a Button for deleting
                    private final Button deleteButton = new Button("Delete");
                    {
                        // Event handler for the Delete button
                        deleteButton.setOnAction(actionEvent -> {
                            // Retrieve the ClassStudent object associated with this row
                            ClassStudent classStudent = getTableView().getItems().get(getIndex());
                            try {
                                // Attempt to delete the ClassStudent object
                                ClassStudent.destroy(classStudent);
                                // Show a success message using AlertMessage
                                AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Deleted!", "Deleted class");
                                // Reload the TableView to reflect the changes
                                reloadTableView();
                            } catch (SQLException e) {
                                // Throw a RuntimeException if an SQL exception occurs
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
        deleteColumn.setCellFactory(cellDeleteFactory);
        classTable.getColumns().add(deleteColumn);
    }

    /*
     * Retrieve a list of ClassStudent objects from the database.
     * @return An ObservableList of ClassStudent objects
     */
    private ObservableList<ClassStudent> getClassStudent(){
        // Initialize an empty ObservableList to store the retrieved ClassStudent objects
        ObservableList<ClassStudent> classes = FXCollections.observableArrayList();
        try {
            // Retrieve a ResultSet of ClassStudent data from the database
            ResultSet results = ClassStudent.getClassStudent(0, 0);
            // Iterate over the ResultSet to extract ClassStudent information
            while (results.next()) {
                // Extract data for each column from the ResultSet
                int classId = results.getInt("id");
                String className = results.getString("name");
                int majorId = results.getInt("major_id");
                String majorName = results.getString("major_name");
                int schoolYearId = results.getInt("school_year_id");
                int schoolYearStart = results.getInt("start_year");
                int schoolYearEnd = results.getInt("end_year");
                String schoolYearName = results.getString("school_year_name");
                // Create a new ClassStudent object with the extracted data
                ClassStudent classStudent = new ClassStudent(classId, className, majorId, majorName, schoolYearId, schoolYearName, schoolYearStart, schoolYearEnd);
                // Add the new ClassStudent object to the list
                classes.add(classStudent);
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during database retrieval
            e.printStackTrace();
        }
        // Return the list of ClassStudent objects
        return classes;
    }

    //Display a dialog for creating a new class.
    public void showDialogCreateClass(){
        // Create a new Stage for the dialog
        Stage stage = new Stage();
        // Set title of the stage
        stage.setTitle("Create Class");
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            // Load the FXML file for the dialog form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/classes/class-add.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve the necessary form fields from the loader's namespace
            addNameField = (TextField) loader.getNamespace().get("addNameField");
            addMajorField = (ComboBox<Major>) loader.getNamespace().get("addMajorField");
            addSchoolYearField = (ComboBox<SchoolYear>) loader.getNamespace().get("addSchoolYearField");
            // Retrieve the list of majors and school years
            getMajor(1);
            getSchoolYears(1);
            // Retrieve the "Create" button and set an event handler for it
            Button addClassButton = (Button) loader.getNamespace().get("createButton");
            addClassButton.setOnAction(actionEvent -> {
                try {
                    // Create a new class based on the form fields
                    ClassStudent.create(addNameField, addMajorField, addSchoolYearField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Created!", "Created class");
                    // Close the stage after creating the class
                    stage.close();
                    // Reload the TableView to reflect the changes
                    reloadTableView();
                } catch (SQLException e) {
                    // Throw a RuntimeException if an SQL exception occurs
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and show the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during the dialog creation
            e.printStackTrace();
        }
    }

    /*
     * Retrieve school years from the database and populate the corresponding ComboBox.
     * @param i Indicates whether to populate the addSchoolYearField or editSchoolYearField.
     */
    private void getSchoolYears(int i){
        try {
            // Retrieve a ResultSet of school years from the database
            ResultSet result = SchoolYear.getSchoolYear();
            // Iterate over the ResultSet to extract school year information
            while (result.next()) {
                // Extract data for each column from the ResultSet
                int schoolYearId = result.getInt("id");
                String schoolYearName = result.getString("name");
                int schoolYearStart = result.getInt("start_year");
                int schoolYearEnd = result.getInt("end_year");
                // Create a new SchoolYear object with the extracted data
                SchoolYear schoolYear = new SchoolYear(schoolYearId, schoolYearName, schoolYearStart, schoolYearEnd);
                // Check the value of i to determine which ComboBox to populate
                if(i == 1){
                    // Populate the addSchoolYearField ComboBox
                    addSchoolYearField.getItems().add(schoolYear);
                } else if (i == 2) {
                    // Populate the editSchoolYearField ComboBox
                    editSchoolYearField.getItems().add(schoolYear);
                }
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during school year retrieval
            e.printStackTrace();
        }
    }

    /*
     * Retrieve majors from the database and populate the corresponding ComboBox.
     * @param i Indicates whether to populate the addMajorField or editMajorField.
     */
    private void getMajor(int i){
        try {
            // Retrieve a ResultSet of majors from the database
            ResultSet result = Major.getMajor();
            // Iterate over the ResultSet to extract major information
            while(result.next()){
                // Extract data for each column from the ResultSet
                int majorId = result.getInt("id");
                String majorName = result.getString("name");
                // Create a new Major object with the extracted data
                Major major = new Major(majorId, majorName);
                // Check the value of i to determine which ComboBox to populate
                if(i == 1){
                    // Populate the addMajorField ComboBox
                    addMajorField.getItems().add(major);
                } else if(i == 2){
                    // Populate the editMajorField ComboBox
                    editMajorField.getItems().add(major);
                }
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during major retrieval
            e.printStackTrace();
        }
    }

    //Reload the TableView with updated data.
    private void reloadTableView() {
        // Retrieve a new list of ClassStudent objects
        ObservableList<ClassStudent> newClasses = getClassStudent();
        // Set the items of the TableView with the new list of ClassStudent objects
        classTable.setItems(newClasses);
    }

    /*
     * Display a dialog for editing a class.
     * @param classStudent The ClassStudent object to be edited.
     */
    public void showDialogEditClass(ClassStudent classStudent){
        // Create a new Stage for the edit dialog
        Stage stage = new Stage();
        // Set title of the stage
        stage.setTitle("Edit Class");
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            // Load the FXML file for the edit dialog form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/classes/class-edit.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve the necessary form fields from the loader's namespace
            editIdField = (TextField) loader.getNamespace().get("editIdField");
            editNameField = (TextField) loader.getNamespace().get("editNameField");
            editMajorField = (ComboBox<Major>) loader.getNamespace().get("editMajorField");
            editSchoolYearField = (ComboBox<SchoolYear>) loader.getNamespace().get("editSchoolYearField");
            // Retrieve the list of majors and school years for editing
            getMajor(2);
            getSchoolYears(2);
            // Set the values of the form fields based on the provided classStudent object
            editIdField.setText(String.valueOf(classStudent.getClassId()));
            editNameField.setText(classStudent.getClassName());
            editMajorField.setValue(new Major(classStudent.getMajorId(), classStudent.getMajorName()));
            editSchoolYearField.setValue(new SchoolYear(classStudent.getSchoolYearId(), classStudent.getSchoolYearName(), classStudent.getSchoolYearStart(), classStudent.getSchoolYearEnd()));
            // Retrieve the "Update" button and set an event handler for it
            Button updateButton = (Button) loader.getNamespace().get("updateButton");
            updateButton.setOnAction(actionEvent -> {
                try {
                    // Update the class based on the edited form fields
                    ClassStudent.update(editIdField, editNameField, editMajorField, editSchoolYearField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated class");
                    // Close the stage after updating the class
                    stage.close();
                    // Reload the TableView to reflect the changes
                    reloadTableView();
                } catch (SQLException e) {
                    // Throw a RuntimeException if an SQL exception occurs
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and show the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during the edit dialog creation
            e.printStackTrace();
        }
    }
}
