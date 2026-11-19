package edu.utsa.cs3443.Lec127_lab4.model;

/**
 * Represents an aid ship that delivers emergency supplies.
 */
public class AidShip extends Ship implements EmergencySupport {

    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Creates an aid ship with the given information.
     */
    public AidShip(String name, String registrationNumber, double tonnage,
                   int crewSize, String currentPort, double currentSpeed,
                   String aidType, int suppliesOnBoard, boolean hasHelipad) {

        super(name, registrationNumber, tonnage, crewSize,
                currentPort, currentSpeed);

        setAidType(aidType);
        setSuppliesOnBoard(suppliesOnBoard);
        setHasHelipad(hasHelipad);
    }

    /**
     * Deploys the aid supplies carried by the ship.
     */
    @Override
    public void deployAid() {
        System.out.println("Aid Ship " + getName()
                + " is deploying " + suppliesOnBoard
                + " units of " + aidType + " aid.");

        unloadSupplies();
    }

    /**
     * Docks the ship and unloads its supplies.
     */
    @Override
    public void dock(String port) {
        super.dock(port);
        unloadSupplies();
    }

    /**
     * Unloads all supplies currently on board.
     */
    private void unloadSupplies() {
        suppliesOnBoard = 0;
    }

    public String getAidType() {
        return aidType;
    }

    public void setAidType(String aidType) {
        if (aidType != null && !aidType.isBlank()) {
            this.aidType = aidType;
        }
    }

    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    public void setSuppliesOnBoard(int suppliesOnBoard) {
        if (suppliesOnBoard >= 0) {
            this.suppliesOnBoard = suppliesOnBoard;
        }
    }

    public boolean getHasHelipad() {
        return hasHelipad;
    }
    public boolean isHasHelipad() {
        return hasHelipad;
    }

    public void setHasHelipad(boolean hasHelipad) {
        this.hasHelipad = hasHelipad;
    }

    /**
     * Returns all information about the aid ship.
     */
    @Override
    public String toString() {
        return "Aid Ship ["
                + super.toString()
                + ", aidType: " + aidType
                + ", suppliesOnBoard: " + suppliesOnBoard
                + ", hasHelipad: " + hasHelipad
                + " ]";
    }
}