package hospitalmanagementsystem.model;

import java.util.*;
import javax.swing.JOptionPane;

public class Doctor {
    private int id;
    private String NAME;
    private String SPEC;
    // Login credentials used by the Doctor Log-In screen
    private String USERNAME;
    private String PASSWORD;
    private List<Integer> assignedPatientIds = new ArrayList<>();
    
    // Creates a new doctor
    public Doctor(int id, String NAME, String SPEC) {
        this.id = id;
        this.NAME = NAME;
        this.SPEC = SPEC;
    }

    // Creates a new doctor with a username and password for logging in
    public Doctor(int id, String NAME, String SPEC, String USERNAME, String PASSWORD) {
        this(id, NAME, SPEC);
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    // Gets doctor information
    public int getId() { return id; }
    public String getName() { return NAME; }
    public String getSpecialty() { return SPEC; }
    public String getUsername() { return USERNAME; }

    // Checks if the given username and password match this doctor's login
    public boolean checkLogin(String user, String pass) {
        return USERNAME != null && PASSWORD != null
                && USERNAME.equals(user) && PASSWORD.equals(pass);
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
        return "Dr. " + NAME + " (" + SPEC + ")";
    }
}