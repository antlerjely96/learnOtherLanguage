package managemark.managemark;

import javafx.scene.control.Alert;

//class to Alert Message
public class AlertMessage {
    // Method to display an alert message with the specified type, title, and content
    public static void getMessage(Alert.AlertType type, String title, String contentType){
        // Create a new alert with the specified type
        Alert alert = new Alert(type);
        // Set the title of the alert
        alert.setTitle(title);
        // Set the header text to null
        alert.setHeaderText(null);
        // Set the main content of the alert
        alert.setContentText(contentType);
        // Display the alert and wait for user interaction
        alert.showAndWait();
    }
}
