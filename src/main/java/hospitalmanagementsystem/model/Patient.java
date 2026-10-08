package hospitalmanagementsystem.model;

import java.util.*;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String medicalHistory;
    private List<Appointments> appointments = new ArrayList<>();

    // Creates a new patient
    public Patient(int id, String name, int age, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }
    // Gets patient information
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMedicalHistory() { return medicalHistory; }
    public List<Appointments> getAppointments() { return appointments; }

    // Changes patient information
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMedicalHistory(String history) {
        this.medicalHistory = history;
    }
}