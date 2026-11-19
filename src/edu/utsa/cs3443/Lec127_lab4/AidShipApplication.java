
package edu.utsa.cs3443.Lec127_lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application for the GERO Aid Ship Management System.
 * Starts the application by displaying the login screen.
 */
public class AidShipApplication extends Application {

    /**
     * Launches the GERO login screen.
     *
     * @param stage the primary application window
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/edu/utsa/cs3443/Lec127_lab3/layouts/login-screen.fxml"
                )
        );

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("GERO - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
