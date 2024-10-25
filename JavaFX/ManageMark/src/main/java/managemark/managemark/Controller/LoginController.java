package managemark.managemark.Controller;

//import classes
import javafx.fxml.FXML;
import javafx.scene.control.*;
import managemark.managemark.AlertMessage;
import managemark.managemark.Main;
import managemark.managemark.Model.User;
import java.sql.ResultSet;

//Controller class for the login functionality.
public class LoginController {
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;

    /*
     * Method to handle the login process.
     * @throws Exception if an exception occurs during login
     */
    public void login() throws Exception {
        // Retrieve email and password input from the fields
        String email = emailField.getText();
        String password = passwordField.getText();
        // Check login credentials and proceed accordingly
        if(checkLogin(email, password)){
            // Show the main view if login is successful
            Main.showMainView();
        } else {
            // Display an error message if login fails
            AlertMessage.getMessage(Alert.AlertType.ERROR, "Login error!", "Invalid email or password");
        }
    }

    /*
     * Check the login credentials against the database.
     * @param email The email input for login
     * @param password The password input for login
     * @return true if login is successful, false otherwise
     */
    private boolean checkLogin(String email, String password) {
        try {
            // Check the login credentials in the database
            ResultSet result = User.checkLogin(email, password);
            if (result.next()) {
                // Display a success message if login is successful
                AlertMessage.getMessage(Alert.AlertType.INFORMATION, "Success", "Login Success");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs during login verification
            e.printStackTrace();
            return false;
        }
    }
}
