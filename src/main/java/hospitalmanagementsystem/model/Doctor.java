package hospitalmanagementsystem.model;

import java.util.*;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    // Login credentials used by the Doctor Log-In screen
    private String username;
    private String password;
    private List<Integer> assignedPatientIds = new ArrayList<>();

    // Creates a new doctor
    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    // Creates a new doctor with a username and password for logging in
    public Doctor(int id, String name, String specialty, String username, String password) {
        this(id, name, specialty);
        this.username = username;
        this.password = password;
    }

    // Gets doctor information
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getUsername() { return username; }

    // Checks if the given username and password match this doctor's login
    public boolean checkLogin(String user, String pass) {
        return username != null && password != null
                && username.equals(user) && password.equals(pass);
    }

    // Assigns a patient to the doctor
    public void assignPatient(int id) {
        assignedPatientIds.add(id);
    }

    // Gets all patients assigned to the doctor
    public List<Integer> getAssignedPatientIds() {
        return assignedPatientIds;
    }

    // Displays doctor information
    public String toString() {
        return "Dr. " + name + " (" + specialty + ")";
    }
}