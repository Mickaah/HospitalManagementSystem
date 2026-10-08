package hospitalmanagementsystem.model;

import java.util.*;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private List<Integer> assignedPatientIds = new ArrayList<>();

    // Creates a new doctor
    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    // Gets doctor information
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

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
