package hospitalmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private List<Integer> assignedPatientIds;

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.assignedPatientIds = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    public void assignPatient(int patientId) {
        assignedPatientIds.add(patientId);
    }

    public List<Integer> getAssignedPatientIds() {
        return assignedPatientIds;
    }

    @Override
    public String toString() {
        return "Dr. " + name + " (" + specialty + ")";
    }
}