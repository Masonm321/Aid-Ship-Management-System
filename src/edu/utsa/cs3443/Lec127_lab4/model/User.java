
package edu.utsa.cs3443.Lec127_lab4.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents an authorized user of the
 * GERO Aid Ship Management System.
 */
public class User extends Person {

    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    /**
     * Creates a user with personal information
     * and login credentials.
     */
    public User(String firstName, String lastName,
                String email, String phoneNumber,
                String username, String password) {

        super(firstName, lastName);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the user's email address.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the username.
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks whether the entered credentials match.
     */
    public boolean validateCredentials(String username,
                                       String password) {

        return this.username.equals(username)
                && this.password.equals(password);
    }

    /**
     * Reads users.csv and searches for matching credentials.
     *
     * @param username entered username
     * @param password entered password
     * @return matching User or null if invalid
     */
    public static User authenticate(String username,
                                    String password) {

        if (username == null || password == null
                || username.isBlank() || password.isBlank()) {
            return null;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader("data/users.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                if (parts.length != 6) {
                    System.err.println(
                            "Skipping invalid user record.");
                    continue;
                }

                User user = new User(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parts[4].trim(),
                        parts[5].trim()
                );

                if (user.validateCredentials(
                        username, password)) {
                    return user;
                }
            }

        } catch (IOException e) {
            System.err.println(
                    "Error reading users.csv: "
                            + e.getMessage());
        }

        return null;
    }

    /**
     * Returns the user's name and username.
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName()
                + " (" + username + ")";
    }
}
