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
import managemark.managemark.Model.Major;
import java.io.IOException;
import java.sql.*;

//Controller class for managing majors.
public class MajorController {
    // Define FXML annotation for majorTable as a TableView representing class data.
    @FXML
    private TableView<Major> majorTable;
    //Define FXML annotation for the TableColumn representing major IDs.
    @FXML
    private TableColumn<Major, Integer> majorIdColumn;
    //Define FXML annotation for the TableColumn representing major names.
    @FXML
    private TableColumn<Major, String> majorNameColumn;
    //Define FXML annotation for the TextField used for adding a major name.
    private TextField addNameField;
    //Define FXML annotation for the TextField used for editing a major ID.
    private TextField editIdField;
    //Define FXML annotation for the TextField used for editing a major name.
    private TextField editNameField;
    //Define a TableColumn for editing major data.
    private final TableColumn<Major, Void> editColumn = new TableColumn<>("Edit");
    //Define a TableColumn for deleting major data.
    private final TableColumn<Major, Void> deleteColumn = new TableColumn<>("Delete");

    //Initialize method called when the Controller is loaded.
    public void initialize(){
        //Get a list of ClassStudent objects
        ObservableList<Major> majors = getMajor();
        // Set the items in the TableView to the retrieved list of majors
        majorTable.setItems(majors);
        // Set cell value factories for various columns to display major data
        majorIdColumn.setCellValueFactory(cellData -> cellData.getValue().majorIdProperty().asObject());
        majorNameColumn.setCellValueFactory(cellData -> cellData.getValue().majorNameProperty());
        // Create a cell factory to generate edit cells in the "Edit" column of the table
        Callback<TableColumn<Major, Void>, TableCell<Major, Void>> cellEditFactory = new Callback<>() {
            @Override
            public TableCell<Major, Void> call(TableColumn<Major, Void> majorVoidTableColumn) {
                //Create a TableCell for Major objects with a custom layout.
                TableCell<Major, Void> cell = new TableCell<>(){
                    // Initialize a Button for editing
                    private final Button editButton = new Button("Edit");
                    {
                        // Handle the event when the "Edit" button is clicked
                        editButton.setOnAction(actionEvent -> {
                            // Retrieve the Major object associated with this row
                            Major major = getTableView().getItems().get(getIndex());
                            // Display the edit dialog for the selected major
                            showDialogEditMajor(major);
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
        // Add the edit column to the majors table
        majorTable.getColumns().add(editColumn);
        // Create a cell factory to generate delete cells in the "Delete" column of the table
        Callback<TableColumn<Major, Void>, TableCell<Major, Void>> cellDeleteFactory = new Callback<>() {
            @Override
            public TableCell<Major, Void> call(TableColumn<Major, Void> majorVoidTableColumn) {
                //Create a TableCell for Major objects with a custom layout.
                TableCell<Major, Void> cell = new TableCell<>(){
                    // Initialize a Button for deleting
                    private final Button deleteButton = new Button("Delete");
                    {
                        // Handle the event when the "Delete" button is clicked
                        deleteButton.setOnAction(ActionEvent -> {
                            // Retrieve the ClassStudent object associated with this row
                            Major major = getTableView().getItems().get(getIndex());
                            try {
                                // Attempt to delete the Major object
                                Major.destroy(major);
                                // Show a success message using AlertMessage
                                AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Deleted!", "Deleted major");
                                // Reload the TableView to reflect the changes
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
        deleteColumn.setCellFactory(cellDeleteFactory);
        majorTable.getColumns().add(deleteColumn);
    }

    // Method to retrieve and populate a list of majors
    private ObservableList<Major> getMajor(){
        // Initialize an ObservableList to store Major objects
        ObservableList<Major> majors = FXCollections.observableArrayList();
        try {
            // Retrieve a ResultSet containing major data
            ResultSet results = Major.getMajor();
            // Iterate through the ResultSet to create Major objects
            while(results.next()){
                // Extract major ID and name from the ResultSet
                int majorId = results.getInt("id");
                String majorName = results.getString("name");
                // Create a new Major object
                Major major = new Major(majorId, majorName);
                // Add the Major object to the list
                majors.add(major);
            }
        } catch (Exception e) {
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        }
        // Return the list of Major objects
        return majors;
    }

    //Display a dialog for creating a new major.
    public void showDialogCreateMajor() {
        // Create a new Stage for the dialog
        Stage stage = new Stage();
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set title of the stage
        stage.setTitle("Add major");
        try {
            // Load the FXML file for the dialog form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/majors/major-add.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve the necessary form fields from the loader's namespace
            addNameField = (TextField) loader.getNamespace().get("addNameField");
            // Retrieve the "Create" button and set an event handler for it
            Button addMajorButton = (Button) loader.getNamespace().get("createButton");
            addMajorButton.setOnAction(actionEvent -> {
                try {
                    // Create a new major based on the form fields
                    Major.create(addNameField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Created!", "Created major");
                    // Close the stage after creating the major
                    stage.close();
                    // Reload the TableView to reflect the changes
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and show the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Reload the TableView with updated data.
    private void reloadTableView(){
        // Retrieve a new list of Major objects
        ObservableList<Major> majors = getMajor();
        // Set the items of the TableView with the new list of Major objects
        majorTable.setItems(majors);
    }

    /*
     * Display a dialog for editing a major.
     * @param major The Major object to be edited.
     */
    public void showDialogEditMajor(Major major){
        // Create a new Stage for the edit dialog
        Stage stage = new Stage();
        // Set the modality of the stage to APPLICATION_MODAL
        stage.initModality(Modality.APPLICATION_MODAL);
        // Set title of the stage
        stage.setTitle("Edit Major");
        try {
            // Load the FXML file for the edit dialog form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managemark/managemark/majors/major-edit.fxml"));
            // Load the VBox form from the FXML file
            VBox form = loader.load();
            // Retrieve the necessary form fields from the loader's namespace
            editIdField = (TextField) loader.getNamespace().get("editIdField");
            editNameField = (TextField) loader.getNamespace().get("editNameField");
            // Set the values of the form fields based on the provided Major object
            editIdField.setText(String.valueOf(major.getMajorId()));
            editNameField.setText(String.valueOf(major.getMajorName()));
            // Retrieve the "Update" button and set an event handler for it
            Button editMajorButton = (Button) loader.getNamespace().get("editButton");
            editMajorButton.setOnAction(actionEvent -> {
                try {
                    // Update the major based on the edited form fields
                    Major.update(editIdField, editNameField);
                    // Show a success message using AlertMessage
                    AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Updated!", "Updated major");
                    // Close the stage after updating the class
                    stage.close();
                    // Reload the TableView to reflect the changes
                    reloadTableView();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // Set the scene of the stage with the form and show the stage
            stage.setScene(new Scene(form));
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
