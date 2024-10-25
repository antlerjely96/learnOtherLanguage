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
import managemark.managemark.Model.Mark;
import managemark.managemark.Model.Subject;
import java.sql.*;

//Controller class for managing mark.
public class MarkController {
    //Define FXML annotation for the ComboBox used for search a class.
    @FXML
    public ComboBox<ClassStudent> addClassField;
    //Define FXML annotation for the ComboBox used for search a subject.
    @FXML
    public ComboBox<Subject> addSubjectField;
    //Define FXML annotation for the Button used for search.
    @FXML
    public Button searchButton;
    //Define FXML annotation for the TableView representing mark data.
    @FXML
    public TableView<Mark> markTable;
    //Define FXML annotation for the TableColumn representing student IDs.
    @FXML
    public TableColumn<Mark, Integer> studentIdColumn;
    //Define FXML annotation for the TableColumn representing student names.
    @FXML
    public TableColumn<Mark, String> studentNameColumn;
    //Define FXML annotation for the TableColumn representing student codes.
    @FXML
    public TableColumn<Mark, String> studentCodeColumn;
    //Define FXML annotation for the TableColumn representing student marks.
    @FXML
    public TableColumn<Mark, Double> studentMarkColumn;
    //Define a TableColumn for change mark data.
    public TableColumn<Mark, Void> changeMarkColumn = new TableColumn<>("Change");
    //Define FXML annotation for the TextField used for editing a student name.
    @FXML
    public TextField editStudentNameField;
    //Define FXML annotation for the TextField used for editing a student mark.
    @FXML
    public TextField editStudentMarkField;

    //Initialize method called when the Controller is loaded.
    public void initialize(){
        // Call the getClassStudent method to retrieve class student data
        getClassStudent();
        // Call the getSubject method to retrieve subject data
        getSubject();
    }

    // Method to retrieve and populate class student data
    private void getClassStudent(){
        try {
            // Retrieve a ResultSet containing class student data
            ResultSet results = ClassStudent.getClassStudent(0, 0);
            // Iterate through the ResultSet to create ClassStudent objects
            while (results.next()){
                // Extract classId and className from the ResultSet
                int classId = results.getInt("id");
                String className = results.getString("name");
                // Create a new ClassStudent object
                ClassStudent classStudent = new ClassStudent(classId, className, 0, "", 0, "", 0, 0);
                // Add the ClassStudent object to the addClassField items
                addClassField.getItems().add(classStudent);
            }
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve and populate subject data
    private void getSubject(){
        try {
            // Retrieve a ResultSet containing subject data
            ResultSet results = Subject.getSubject();
            // Iterate through the ResultSet to create Subject objects
            while (results.next()){
                // Extract subjectId and subjectName from the ResultSet
                int subjectId = results.getInt("id");
                String subjectName = results.getString("name");
                // Create a new Subject object
                Subject subject = new Subject(subjectId, subjectName, 0);
                // Add the Subject object to the addSubjectField items
                addSubjectField.getItems().add(subject);
            }
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to display a dialog for marking details
    public void showDialog() {
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set the title of the stage
        stage.setTitle("Mark detail");
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            // Load the FXML file for the mark detail form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/marks/mark-detail.fxml"));
            // Load the form as a VBox
            VBox form = loader.load();
            // Initialize table and columns from the FXML file
            markTable = (TableView<Mark>) loader.getNamespace().get("markTable");
            studentIdColumn = (TableColumn<Mark, Integer>) loader.getNamespace().get("studentIdColumn");
            studentCodeColumn = (TableColumn<Mark, String>) loader.getNamespace().get("studentNameColumn");
            studentNameColumn = (TableColumn<Mark, String>) loader.getNamespace().get("studentCodeColumn");
            studentMarkColumn = (TableColumn<Mark, Double>) loader.getNamespace().get("studentMarkColumn");
            // Retrieve marks data and set the items in the table
            ObservableList<Mark> marks = getMark();
            markTable.setItems(marks);
            // Set cell value factories for the table columns
            studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty().asObject());
            studentCodeColumn.setCellValueFactory(cellData -> cellData.getValue().studentCodeProperty());
            studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().studentNameProperty());
            studentMarkColumn.setCellValueFactory(cellData -> cellData.getValue().markProperty().asObject());
            // Callback for creating Change button in a TableCell
            Callback<TableColumn<Mark, Void>, TableCell<Mark, Void>> cellChangeFactory = new Callback<>() {
                @Override
                public TableCell<Mark, Void> call(TableColumn<Mark, Void> markVoidTableColumn) {
                    //Create a TableCell for Mark objects with a change button.
                    TableCell<Mark, Void> cell = new TableCell<>(){
                        // Initialize a Button for change
                        private Button changeButton = new Button("Change");
                        {
                            // Event handler for the Change button
                            changeButton.setOnAction(actionEvent -> {
                                // Retrieve the Mark object associated with this row
                                Mark mark = getTableView().getItems().get(getIndex());
                                // Show a dialog for editing the mark
                                showDialogChangeMark(mark);
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
                                // If the cell is not empty, display the changeButton
                                setGraphic(changeButton);
                            }
                        }
                    };
                    return cell;
                }
            };
            // Set the cell factory for the Change column
            changeMarkColumn.setCellFactory(cellChangeFactory);
            markTable.getColumns().add(changeMarkColumn);
            // Set the scene of the stage with the form and show the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * Retrieve a list of Mark objects from the database.
     * @return An ObservableList of Mark objects
     */
    private ObservableList<Mark> getMark(){
        // Initialize an ObservableList to store Mark objects
        ObservableList<Mark> marks = FXCollections.observableArrayList();
        try {
            // Retrieve a ResultSet containing mark data based on selected subject and class
            ResultSet results = Mark.getMark(addSubjectField.getValue().getSubjectId(), addClassField.getValue().getClassId());
            // Iterate through the ResultSet to create Mark objects
            while (results.next()){
                // Extract studentId, studentCode, studentName, and studentMark from the ResultSet
                int studentId = results.getInt("id");
                String studentCode = results.getString("code");
                String studentName = results.getString("name");
                double studentMark = results.getDouble("mark");
                // Create a new Mark object
                Mark mark = new Mark(studentId, addSubjectField.getValue().getSubjectId(), studentMark, 1, studentName, addSubjectField.getValue().getSubjectName(), studentCode);
                // Add the Mark object to the list
                marks.add(mark);
            }
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
        // Return the list of Mark objects
        return marks;
    }

    // Method to display a dialog for changing a mark
    public void showDialogChangeMark(Mark mark){
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set title of the stage
        stage.setTitle("Change mark");
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            // Load the FXML file for the mark change form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/marks/mark-change.fxml"));
            // Load the form as a VBox
            VBox form = loader.load();
            // Initialize editStudentNameField and editStudentMarkField from the FXML file
            editStudentNameField = (TextField) loader.getNamespace().get("editStudentNameField");
            editStudentMarkField = (TextField) loader.getNamespace().get("editStudentMarkField");
            // Set text in the text fields to the student's name and mark
            editStudentNameField.setText(mark.getStudentName());
            editStudentMarkField.setText(String.valueOf(mark.getMark()));
            // Initialize the saveButton and define its action
            Button saveButton = (Button) loader.getNamespace().get("saveButton");
            saveButton.setOnAction(actionEvent -> {
                try {
                    // Update the mark based on the edited form fields
                    Mark.changeMark(mark, editStudentMarkField, addSubjectField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated mark");
                    // Close the stage after updating the mark
                    stage.close();
                    // Reload the TableView to reflect the changes
                    reloadTableView();
                } catch (SQLException e) {
                    // Throw a RuntimeException if an SQL exception occurs
                    throw new RuntimeException(e);
                }
            });
            // Set the scene for the stage with the form and display
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e){
            // Print the stack trace if an exception occurs during the edit dialog creation
            e.printStackTrace();
        }
    }

    // Method to reload the TableView with updated mark items
    private void reloadTableView() {
        // Retrieve a new list of Mark items
        ObservableList<Mark> newItems = getMark();
        // Set the TableView items to the new list
        markTable.setItems(newItems);
    }
}
