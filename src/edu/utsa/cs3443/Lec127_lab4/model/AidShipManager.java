package edu.utsa.cs3443.Lec127_lab4.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages a collection of AidShip objects.
 * Handles loading, searching, updating, deleting,
 * and saving aid ship data.
 */
public class AidShipManager {

    private ArrayList<AidShip> aidShipList;

    /**
     * Creates an empty list of aid ships.
     */
    public AidShipManager() {
        aidShipList = new ArrayList<>();
    }

    /**
     * Returns the list of aid ships.
     *
     * @return the list of aid ships
     */
    public ArrayList<AidShip> getAidShipList() {
        return aidShipList;
    }

    /**
     * Sets the list of aid ships.
     *
     * @param aidShipList the new list of aid ships
     */
    public void setAidShipList(ArrayList<AidShip> aidShipList) {
        this.aidShipList = aidShipList;
    }

    /**
     * Adds an aid ship to the list.
     *
     * @param aidShip the aid ship to add
     */
    public void addAidShip(AidShip aidShip) {
        aidShipList.add(aidShip);
    }

    /**
     * Finds an aid ship using its registration number.
     *
     * @param registrationNumber the registration number to search for
     * @return the matching AidShip, or null if it is not found
     */
    public AidShip findAidShip(String registrationNumber) {
        for (AidShip aidShip : aidShipList) {
            if (aidShip.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return aidShip;
            }
        }

        return null;
    }

    /**
     * Checks whether an aid ship exists.
     *
     * @param registrationNumber the registration number to search for
     * @return true if the ship exists, otherwise false
     */
    public boolean isAidShipExists(String registrationNumber) {
        return findAidShip(registrationNumber) != null;
    }

    /**
     * Loads aid ships from the CSV data file.
     *
     * @throws IOException if there is a problem reading the file
     */
    public void loadAidShips() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader("data/aid_ships.csv"));

        String line;

        // Skip the header line
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            AidShip aidShip = convertLineToAidShip(line);
            addAidShip(aidShip);
        }

        reader.close();
    }

    /**
     * Converts one line from the CSV file into an AidShip object.
     *
     * @param line one line of aid ship data
     * @return an AidShip created from the line
     */
    private AidShip convertLineToAidShip(String line) {
        String[] data = line.split(",");

        String name = data[0].trim();
        String registrationNumber = data[1].trim();
        double tonnage = Double.parseDouble(data[2].trim());
        int crewSize = Integer.parseInt(data[3].trim());
        String currentPort = data[4].trim();
        String aidType = data[5].trim();
        int suppliesOnBoard = Integer.parseInt(data[6].trim());
        boolean hasHelipad = Boolean.parseBoolean(data[7].trim());


        AidShip aidShip = new AidShip(
                name,
                registrationNumber,
                tonnage,
                crewSize,
                currentPort,
                0.0,
                aidType,
                suppliesOnBoard,
                hasHelipad
        );

        return aidShip;
    }

    /**
     * Converts an AidShip object into a line for the CSV file.
     *
     * @param aidShip the aid ship to convert
     * @return the ship information formatted as a CSV line
     */
    private String convertAidShipToLine(AidShip aidShip) {
        return aidShip.getName() + "," +
                aidShip.getRegistrationNumber() + "," +
                aidShip.getTonnage() + "," +
                aidShip.getCrewSize() + "," +
                aidShip.getCurrentPort() + "," +
                aidShip.getAidType() + "," +
                aidShip.getSuppliesOnBoard() + "," +
                aidShip.getHasHelipad();
    }

    /**
     * Saves all aid ship data to the CSV file.
     *
     * @throws IOException if there is a problem writing to the file
     */
    private void saveDataToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("data/aid_ships.csv"));

        writer.write("name,registration number,tonnage,crew size,port,aid type,aid capacity,has helipad");
        writer.newLine();

        for (AidShip aidShip : aidShipList) {
            writer.write(convertAidShipToLine(aidShip));
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Updates an existing aid ship and saves the changes to the file.
     *
     * @param aidShip the aid ship to update
     * @return true if the ship was updated, otherwise false
     * @throws IOException if there is a problem writing to the file
     */
    public boolean updateAidShip(AidShip aidShip) throws IOException {
        if (aidShipList.contains(aidShip)) {
            saveDataToFile();
            return true;
        }

        return false;
    }
    /**
     * Deletes an aid ship from the list and saves the changes to the file.
     *
     * @param aidShip the aid ship to delete
     * @return true if the ship was deleted, otherwise false
     * @throws IOException if there is a problem writing to the file
     */
    public boolean deleteAidShip(AidShip aidShip) throws IOException {
        boolean removed = aidShipList.remove(aidShip);

        if (removed) {
            saveDataToFile();
            return true;
        }

        return false;
    }
}