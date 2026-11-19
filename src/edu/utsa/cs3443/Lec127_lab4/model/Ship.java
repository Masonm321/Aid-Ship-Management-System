package edu.utsa.cs3443.Lec127_lab4.model;

/**
 * Represents a general ship in the disaster response simulation.
 */
public abstract class Ship implements Navigable {

    private String name;
    private String registrationNumber;
    private double tonnage;
    private int crewSize;
    private double currentSpeed;
    private String currentPort;

    /**
     * Creates a ship with the given information.
     */
    public Ship(String name, String registrationNumber, double tonnage,
                int crewSize, String currentPort, double currentSpeed) {

        setName(name);
        setRegistrationNumber(registrationNumber);
        setTonnage(tonnage);
        setCrewSize(crewSize);
        setCurrentPort(currentPort);
        setCurrentSpeed(currentSpeed);
    }

    /**
     * Navigates the ship toward a destination.
     */
    @Override
    public void navigateTo(String destination, double speed) {
        if (destination != null && !destination.isBlank() && speed >= 0) {
            setCurrentSpeed(speed);

            System.out.println(getName() + " is navigating to "
                    + destination + " at speed " + speed + ".");
        }
    }

    /**
     * Docks the ship at a port and stops its movement.
     */
    public void dock(String port) {
        if (port != null && !port.isBlank()) {
            setCurrentPort(port);
            setCurrentSpeed(0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber != null && !registrationNumber.isBlank()) {
            this.registrationNumber = registrationNumber;
        }
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        if (tonnage >= 0) {
            this.tonnage = tonnage;
        }
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        if (crewSize >= 0) {
            this.crewSize = crewSize;
        }
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        if (currentSpeed >= 0) {
            this.currentSpeed = currentSpeed;
        }
    }

    public String getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(String currentPort) {
        if (currentPort != null && !currentPort.isBlank()) {
            this.currentPort = currentPort;
        }
    }

    /**
     * Returns all general ship information.
     */
    @Override
    public String toString() {
        return "name: " + name
                + ", registrationNumber: " + registrationNumber
                + ", tonnage: " + tonnage
                + ", crewSize: " + crewSize
                + ", currentSpeed: " + currentSpeed
                + ", currentPort: " + currentPort;
    }
}