
package edu.utsa.cs3443.Lec127_lab4;

import edu.utsa.cs3443.Lec127_lab4.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controls the login screen for the GERO system.
 */
public class LoginScreenController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    /**
     * Validates login information and opens the dashboard.
     */
    @FXML
    private void handleLogin() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText(
                    "Please enter a username and password.");
            return;
        }

        User user = User.authenticate(username, password);

        if (user == null) {
            errorLabel.setText(
                    "Invalid username or password.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/edu/utsa/cs3443/Lec127_lab3/layouts/main-screen.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) usernameField
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("GERO - Aid Ship Dashboard");
            stage.show();

        } catch (IOException e) {
            errorLabel.setText(
                    "Unable to open the dashboard.");
            e.printStackTrace();
        }
    }
}
