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
import managemark.managemark.Model.Subject;
import java.sql.*;

//Controller class for managing class of subject.
public class SubjectController {
    // TableView to display subjects
    @FXML
    private TableView<Subject> subjectTable;
    // Column for subject IDs
    @FXML
    private TableColumn<Subject, Integer> subjectIdColumn;
    // Column for subject names
    @FXML
    private TableColumn<Subject, String> subjectNameColumn;
    // Column for subject durations
    @FXML
    private TableColumn<Subject, Integer> subjectDurationColumn;
    // Edit column for subject table
    private final TableColumn<Subject, Void> editColumn = new TableColumn<>("Edit");
    // Delete column for subject table
    private final TableColumn<Subject, Void> deleteColumn = new TableColumn<>("Delete");
    // Text field for editing subject ID
    @FXML
    public TextField editIdField;
    // Text field for editing subject name
    @FXML
    public TextField editNameField;
    // Text field for editing subject duration
    @FXML
    public TextField editDurationField;
    // Text field for adding a new subject name
    @FXML TextField addNameField;
    // Text field for adding a new subject duration
    @FXML TextField addDurationField;

    // Initialize method to set up the subject table and columns
    public void initialize(){
        try {
            // Retrieve the list of subjects
            ObservableList<Subject> subjects = getSubject();
            // Set subjects list in the subject table
            subjectTable.setItems(subjects);
            // Set cell value factories
            subjectIdColumn.setCellValueFactory(cellData -> cellData.getValue().subjectIdProperty().asObject());
            subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().subjectNameProperty());
            subjectDurationColumn.setCellValueFactory(cellData -> cellData.getValue().subjectDurationProperty().asObject());
            // Define a cell factory for the edit button in each row
            Callback<TableColumn<Subject, Void>, TableCell<Subject, Void>> cellEditFactory = new Callback<>(){
                @Override
                public TableCell<Subject, Void> call(TableColumn<Subject, Void> subjectVoidTableColumn) {
                    //Create a TableCell for Subject objects with a custom layout.
                    TableCell<Subject, Void> cell = new TableCell<>(){
                        // Initialize a Button for editing
                        private final Button editButton = new Button("Edit");
                        {
                            // Event handler for the Edit button
                            editButton.setOnAction(actionEvent -> {
                                // Retrieve the subject associated with the clicked row
                                Subject subject = getTableView().getItems().get(getIndex());
                                // show the edit dialog
                                showDialogEditSubject(subject);
                            } );
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
            // Add the edit column to the Table
            subjectTable.getColumns().add(editColumn);
            // Define a cell factory for the delete button in each row
            Callback<TableColumn<Subject, Void>, TableCell<Subject, Void>> cellDeleteFactory = new Callback<>(){
                @Override
                public TableCell<Subject, Void> call(TableColumn<Subject, Void> subjectVoidTableColumn) {
                    //Create a TableCell for Subject objects with a delete button.
                    TableCell<Subject, Void> cell = new TableCell<>(){
                        // Initialize a Button for deleting
                        private final Button deleteButton = new Button("Delete");
                        {
                            // Event handler for the Delete button
                            deleteButton.setOnAction(actionEvent -> {
                                // Retrieve the subject associated with the clicked row
                                Subject subject = getTableView().getItems().get(getIndex());
                                try {
                                    // delete selected subject
                                    Subject.destroy(subject);
                                    // Show a success message using AlertMessage
                                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Deleted!", "Deleted subject");
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
            // Add the delete column to the Table
            subjectTable.getColumns().add(deleteColumn);

        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve a list of subjects from the database and return them as an ObservableList
    private ObservableList<Subject> getSubject(){
        // Initialize an ObservableList to store the subjects
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            // Retrieve results from the database for subjects
            ResultSet results = Subject.getSubject();
            // Iterate over the ResultSet to extract ClassStudent information
            while (results.next()) {
                // Extract data for each column from the ResultSet
                int subjectId = results.getInt("id");
                String subjectName = results.getString("name");
                int subjectDuration = results.getInt("duration");
                // Create a new Subject object with the extracted data
                Subject subject = new Subject(subjectId, subjectName, subjectDuration);
                // Add the new Subject object to the list
                subjects.add(subject);
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
        // Return the ObservableList of subjects
        return subjects;
    }

    // Method to display the edit dialog for a specific subject
    public void showDialogEditSubject(Subject subject){
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set modality to block input events to other windows
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set the title of the dialog window
        stage.setTitle("Edit Subject");
        try {
            // Load the FXML file for the edit subject form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/subjects/subject-edit.fxml"));
            // Load the FXML content into a VBox
            VBox form = loader.load();
            // Retrieve the text fields from the FXML file using their IDs
            editIdField = (TextField) loader.getNamespace().get("editIdField");
            editNameField = (TextField) loader.getNamespace().get("editNameField");
            editDurationField = (TextField) loader.getNamespace().get("editDurationField");
            // Populate the text fields with the subject details
            editIdField.setText(String.valueOf(subject.getSubjectId()));
            editNameField.setText(subject.getSubjectName());
            editDurationField.setText(String.valueOf(subject.getSubjectDuration()));
            // Retrieve the update button from the FXML file and set its action
            Button editSubjectButton = (Button) loader.getNamespace().get("updateButton");
            editSubjectButton.setOnAction(actionEvent -> {
                try {
                    // Update the subject with the new details
                    Subject.update(editIdField, editNameField, editDurationField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated subject");
                    // Close the dialog window
                    stage.close();
                    // Reload the table view with the updated data
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form content and display the dialog
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }

    // Method to reload the data in the subject table
    private void reloadTableView() {
        // Retrieve a new list of subjects from the database
        ObservableList<Subject> newSubjects = getSubject();
        // Set the new list of subjects in the subject table to refresh the table view
        subjectTable.setItems(newSubjects);
    }

    // Method to display the dialog for creating a new subject
    public void showDialogCreateSubject() {
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set modality to block input events to other windows
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set the title of the dialog window
        stage.setTitle("Add Subject");
        try {
            // Load the FXML file for the add subject form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/subjects/subject-add.fxml"));
            // Load the FXML content into a VBox
            VBox form = loader.load();
            // Retrieve the text fields from the FXML file using their IDs
            addNameField = (TextField) loader.getNamespace().get("addNameField");
            addDurationField = (TextField) loader.getNamespace().get("addDurationField");
            // Retrieve the create button from the FXML file and set its action
            Button AddSubjectButton = (Button) loader.getNamespace().get("createButton");
            AddSubjectButton.setOnAction(actionEvent -> {
                try {
                    // Create a new subject with the provided details
                    Subject.create(addNameField, addDurationField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Created!", "Created subject");
                    // Close the dialog window
                    stage.close();
                    // Reload the table view with the updated data
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form content and display the dialog
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
