
package edu.utsa.cs3443.Lec127_lab4;

import edu.utsa.cs3443.Lec127_lab4.model.AidShip;
import edu.utsa.cs3443.Lec127_lab4.model.AidShipManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Controls the GERO Aid Ship Management dashboard.
 * Allows users to list, find, delete, sort, and log out.
 */
public class MainScreenController {

    @FXML
    private Button listShipsButton;

    @FXML
    private Button goButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextArea registrationNumberArea;

    @FXML
    private TextArea resultsArea;

    @FXML
    private RadioButton findRadioButton;

    @FXML
    private RadioButton deleteRadioButton;

    @FXML
    private RadioButton sortNameRadioButton;

    @FXML
    private RadioButton sortRegistrationRadioButton;

    private AidShipManager manager;

    /**
     * Initializes the dashboard and loads ship data.
     */
    @FXML
    public void initialize() {

        manager = new AidShipManager();

        try {
            manager.loadAidShips();
        } catch (IOException e) {
            resultsArea.setText(
                    "Error loading ships: " + e.getMessage()
            );
        }

        ToggleGroup actionGroup = new ToggleGroup();
        findRadioButton.setToggleGroup(actionGroup);
        deleteRadioButton.setToggleGroup(actionGroup);

        ToggleGroup sortGroup = new ToggleGroup();
        sortNameRadioButton.setToggleGroup(sortGroup);
        sortRegistrationRadioButton.setToggleGroup(sortGroup);

        sortNameRadioButton.setSelected(true);

        listShipsButton.setOnAction(event -> listShips());
        goButton.setOnAction(event -> handleGo());
        logoutButton.setOnAction(event -> handleLogout());

        sortNameRadioButton.setOnAction(event -> listShips());
        sortRegistrationRadioButton.setOnAction(event -> listShips());
    }

    /**
     * Displays ships sorted by the selected option.
     */
    private void listShips() {

        ArrayList<AidShip> ships =
                new ArrayList<>(manager.getAidShipList());

        if (sortNameRadioButton.isSelected()) {

            ships.sort(
                    Comparator.comparing(
                            AidShip::getName,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );

        } else if (sortRegistrationRadioButton.isSelected()) {

            ships.sort(
                    Comparator.comparing(
                            AidShip::getRegistrationNumber,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );
        }

        StringBuilder output = new StringBuilder();

        for (AidShip ship : ships) {
            output.append(ship.toString());
            output.append("\n\n");
        }

        if (output.length() == 0) {
            resultsArea.setText("No aid ships found.");
        } else {
            resultsArea.setText(output.toString());
        }
    }

    /**
     * Finds or deletes a ship by registration number.
     */
    private void handleGo() {

        String registrationNumber =
                registrationNumberArea.getText().trim();

        if (registrationNumber.isEmpty()) {
            resultsArea.setText(
                    "Please enter a registration number."
            );
            return;
        }

        if (!findRadioButton.isSelected()
                && !deleteRadioButton.isSelected()) {
            resultsArea.setText(
                    "Please select Find or Delete."
            );
            return;
        }

        AidShip ship = manager.findAidShip(registrationNumber);

        if (ship == null) {
            resultsArea.setText(
                    "No ship found with registration number: "
                            + registrationNumber
            );
            return;
        }

        if (findRadioButton.isSelected()) {

            resultsArea.setText(ship.toString());

        } else if (deleteRadioButton.isSelected()) {

            try {
                boolean deleted = manager.deleteAidShip(ship);

                if (deleted) {
                    resultsArea.setText(
                            "Ship deleted successfully:\n"
                                    + ship.toString()
                    );
                    registrationNumberArea.clear();
                } else {
                    resultsArea.setText(
                            "Unable to delete ship."
                    );
                }

            } catch (IOException e) {
                resultsArea.setText(
                        "Error deleting ship: " + e.getMessage()
                );
            }
        }
    }

    /**
     * Returns the user to the login screen.
     */
    private void handleLogout() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/edu/utsa/cs3443/Lec127_lab3/layouts/login-screen.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) logoutButton
                    .getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("GERO - Login");
            stage.show();

        } catch (IOException e) {
            resultsArea.setText(
                    "Error logging out: " + e.getMessage()
            );
        }
    }
}
