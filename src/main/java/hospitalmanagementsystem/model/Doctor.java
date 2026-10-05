package hospitalmanagementsystem.model;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name, specialty;
    private List<Patient> patients = new ArrayList<>();

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }
    public Doctor() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<Patient> getPatients() { return patients; }
    public void addPatient(Patient p) { this.patients.add(p); }

    @Override
    public String toString() {
        return "Doctor ID: " + id + " | Dr. " + name + " (" + specialty + ")";
    }
}