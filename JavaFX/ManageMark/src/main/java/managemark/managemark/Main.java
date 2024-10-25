package managemark.managemark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // Static variable to store the primary stage of the application
    private static Stage primaryStage;
    // Start method called when the application is launched
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the primary stage of the application
        Main.primaryStage = primaryStage;
        // Show the login view
        showLogin();
    }

    // Method to show the login view
    public static void showLogin() throws Exception {
        // Load the Login.fxml file
        Parent root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
        // Set the scene with the login view
        primaryStage.setScene(new Scene(root));
        // Display the login view
        primaryStage.show();
    }

    // Method to show the main view
    public static void showMainView() throws Exception {
        // Load the Main.fxml file
        Parent root = FXMLLoader.load(Main.class.getResource("Main.fxml"));
        // Set the scene with the main view
        primaryStage.setScene(new Scene(root));
        // Display the main view
        primaryStage.show();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Launch the application
        launch();
    }
}
