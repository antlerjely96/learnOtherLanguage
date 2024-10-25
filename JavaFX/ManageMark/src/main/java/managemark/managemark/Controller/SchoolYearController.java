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
import managemark.managemark.Model.SchoolYear;
import java.sql.*;

//Controller class for managing school year.
public class SchoolYearController {
    // Define FXML annotation for schoolYearTable as a TableView representing school year data.
    @FXML
    public TableView<SchoolYear> schoolYearTable;
    //Define FXML annotation for the TableColumn representing school year IDs.
    @FXML
    public TableColumn<SchoolYear, Integer> schoolYearIdColumn;
    //Define FXML annotation for the TableColumn representing school year names.
    @FXML
    public TableColumn<SchoolYear, String> schoolYearNameColumn;
    //Define FXML annotation for the TableColumn representing school year start.
    @FXML
    public TableColumn<SchoolYear, Integer> schoolYearStartColumn;
    //Define FXML annotation for the TableColumn representing school year end.
    @FXML
    public TableColumn<SchoolYear, Integer> schoolYearEndColumn;
    //Define FXML annotation for the TextField used for adding a school year name.
    @FXML
    public TextField addNameField;
    //Define FXML annotation for the TextField used for adding a school year start.
    @FXML
    public TextField addStartField;
    //Define FXML annotation for the TextField used for adding a school year end.
    @FXML
    public TextField addEndField;
    //Define a TableColumn for editing school year data.
    public TableColumn<SchoolYear, Void> editColumn = new TableColumn<>("Edit");
    //Define FXML annotation for the TextField used for editing a school year ID.
    @FXML
    public TextField editIdField;
    //Define FXML annotation for the TextField used for editing a school year name.
    @FXML
    public TextField editNameField;
    //Define FXML annotation for the TextField used for editing a school year start.
    @FXML
    public TextField editStartField;
    //Define FXML annotation for the TextField used for editing a school year end.
    @FXML
    public TextField editEndField;
    //Define a TableColumn for deleting school year data.
    public TableColumn<SchoolYear, Void> deleteColumn = new TableColumn<>("Delete");

    //Initialize method called when the Controller is loaded.
    public void initialize(){
        //Get a list of SchoolYear objects
        ObservableList<SchoolYear> schoolYears = getSchoolYears();
        // Set the items in the TableView to the retrieved list of majors
        schoolYearTable.setItems(schoolYears);
        // Set cell value factories for various columns to display school year data
        schoolYearIdColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearIdProperty().asObject());
        schoolYearNameColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearNameProperty());
        schoolYearStartColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearStartProperty().asObject());
        schoolYearEndColumn.setCellValueFactory(cellData -> cellData.getValue().schoolYearEndProperty().asObject());
        // Callback for creating Edit button in a TableCell
        Callback<TableColumn<SchoolYear, Void>, TableCell<SchoolYear, Void>> cellEditFactory = new Callback<>(){
            @Override
            public TableCell<SchoolYear, Void> call(TableColumn<SchoolYear, Void> schoolYearVoidTableColumn) {
                //Create a TableCell for School year objects with a custom layout.
                TableCell<SchoolYear, Void> cell = new TableCell<>(){
                    // Initialize a Button for editing
                    private final Button editButton = new Button("Edit");
                    {
                        // Handle the event when the "Edit" button is clicked
                        editButton.setOnAction(actionEvent -> {
                            // Retrieve the SchoolYear object associated with this row
                            SchoolYear schoolYear = getTableView().getItems().get(getIndex());
                            // Display the edit dialog for the selected school year
                            showDialogEditSchoolYear(schoolYear);
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
        // Set a new cell factory for the edit column
        editColumn.setCellFactory(cellEditFactory);
        // Add the edit column to the schoolYearTable
        schoolYearTable.getColumns().add(editColumn);
        // Callback for creating Delete button in a TableCell
        Callback<TableColumn<SchoolYear, Void>, TableCell<SchoolYear, Void>> cellDeleteFactory = new Callback<>() {
            @Override
            public TableCell<SchoolYear, Void> call(TableColumn<SchoolYear, Void> schoolYearVoidTableColumn) {
                // Initialize a Button for deleting
                TableCell<SchoolYear, Void> cell = new TableCell<>(){
                    private final Button deleteButton = new Button("Delete");
                    {
                        // Handle the event when the "Delete" button is clicked
                        deleteButton.setOnAction(actionEvent -> {
                            // Retrieve the SchoolYear object associated with this row
                            SchoolYear schoolYear = getTableView().getItems().get(getIndex());
                            try {
                                // Attempt to delete the SchoolYear object
                                SchoolYear.destroy(schoolYear);
                                // Show a success message using AlertMessage
                                AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Deleted!", "Deleted school year");
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
        schoolYearTable.getColumns().add(deleteColumn);
    }

    // Method to retrieve and populate school years data
    private ObservableList<SchoolYear> getSchoolYears() {
        // Initialize an ObservableList to store SchoolYear objects
        ObservableList<SchoolYear> schoolYears = FXCollections.observableArrayList();
        try {
            // Retrieve a ResultSet containing school year data
            ResultSet results = SchoolYear.getSchoolYear();
            // Iterate through the ResultSet to create SchoolYear objects
            while (results.next()) {
                // Extract school year ID, name, start year, and end year from the ResultSet
                int schoolYearId = results.getInt("id");
                String schoolYearName = results.getString("name");
                int schoolYearStart = results.getInt("start_year");
                int schoolYearEnd = results.getInt("end_year");
                // Create a new SchoolYear object
                SchoolYear schoolYear = new SchoolYear(schoolYearId, schoolYearName, schoolYearStart, schoolYearEnd);
                // Add the SchoolYear object to the list
                schoolYears.add(schoolYear);
            }
        } catch (Exception e){
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
        // Return the list of SchoolYear objects
        return schoolYears;
    }

    // Method to display a dialog for creating a new school year
    public void showDialogCreateSchoolYear() {
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set title of the stage
        stage.setTitle("Add School Year");
        try {
            // Load the FXML file for adding a new school year
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/school-year/school-year-add.fxml"));
            // Load the form as a VBox
            VBox form = loader.load();
            // Initialize text fields for name, start year, and end year from the FXML file
            addNameField = (TextField) loader.getNamespace().get("addNameField");
            addStartField = (TextField) loader.getNamespace().get("addStartField");
            addEndField = (TextField) loader.getNamespace().get("addEndField");
            // Update the end year field based on the start year field input
            addStartField.setOnKeyReleased(keyEvent -> {
                if (!addStartField.getText().equals("")) {
                    addEndField.setText(String.valueOf(Integer.parseInt(addStartField.getText()) + 3));
                }
            });
            // Initialize the create button and define its action
            Button addSchoolYearButton = (Button) loader.getNamespace().get("createButton");
            addSchoolYearButton.setOnAction(actionEvent -> {
                try {
                    // Create a new school year based on the form fields
                    SchoolYear.create(addNameField, addStartField, addEndField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Created!", "Created school year");
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

    // Method to reload the TableView with updated school year items
    private void reloadTableView(){
        // Retrieve a new list of SchoolYear items
        ObservableList<SchoolYear> newSchoolYears = getSchoolYears();
        // Set the TableView items to the new list
        schoolYearTable.setItems(newSchoolYears);
    }

    // Method to display a dialog for editing a school year
    public void showDialogEditSchoolYear(SchoolYear schoolYear){
        // Create a new stage for the dialog
        Stage stage = new Stage();
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set title of the stage
        stage.setTitle("Edit School Year");
        try {
            // Load the FXML file for editing a school year
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/school-year/school-year-edit.fxml"));
            // Load the form as a VBox
            VBox form = loader.load();
            // Initialize text fields for ID, name, start year, and end year from the FXML file
            editIdField = (TextField) loader.getNamespace().get("editIdField");
            editNameField = (TextField) loader.getNamespace().get("editNameField");
            editStartField = (TextField) loader.getNamespace().get("editStartField");
            editEndField = (TextField) loader.getNamespace().get("editEndField");
            // Set text in the text fields to the values of the passed school year
            editIdField.setText(String.valueOf(schoolYear.getSchoolYearId()));
            editNameField.setText(schoolYear.getSchoolYearName());
            editStartField.setText(String.valueOf(schoolYear.getSchoolYearStart()));
            editEndField.setText(String.valueOf(schoolYear.getSchoolYearEnd()));
            // Update the end year field based on the start year field input
            editStartField.setOnKeyReleased(keyEvent -> {
                if (!editStartField.getText().equals("")) {
                    editEndField.setText(String.valueOf(Integer.parseInt(editStartField.getText()) + 3));
                }
            });
            // Initialize the edit button and define its action
            Button editSchoolYearButton = (Button) loader.getNamespace().get("editButton");
            editSchoolYearButton.setOnAction(actionEvent -> {
                try {
                    // Update the school year based on the edited form fields
                    SchoolYear.update(editIdField, editNameField, editStartField, editEndField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated school year");
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
