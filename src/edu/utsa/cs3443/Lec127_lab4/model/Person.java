
package edu.utsa.cs3443.Lec127_lab4.model;

/**
 * Represents a person in the GERO management system.
 */
public class Person {

    private String firstName;
    private String lastName;

    /**
     * Creates a person with a first and last name.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
