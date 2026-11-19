package edu.utsa.cs3443.Lec127_lab4.model;

/**
 * Represents a unit that is capable of navigation.
 */
public interface Navigable {

    /**
     * Navigates the unit toward a destination at a given speed.
     *
     * @param destination the destination
     * @param speed the speed of the unit
     */
    void navigateTo(String destination, double speed);
}