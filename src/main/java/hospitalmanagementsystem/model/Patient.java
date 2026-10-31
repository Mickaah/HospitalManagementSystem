package hospitalmanagementsystem.model;

import java.util.*;

public class Patient {
    private int id;
    private String name;
    private int age;
    // Stores the patient's gender, which was previously being saved into medicalHistory
    private String gender;
    private String medicalHistory;
    private List<Appointments> appointments = new ArrayList<>();

    // Creates a new patient
    public Patient(int id, String name, int age, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    // Creates a new patient with gender; the gender and the medical history are kept in separate fields
    public Patient(int id, String name, int age, String gender, String medicalHistory) {
        this(id, name, age, medicalHistory);
        this.gender = gender;
    }

    // Gets patient information
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getMedicalHistory() { return medicalHistory; }
    public List<Appointments> getAppointments() { return appointments; }

    // Changes patient information
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setMedicalHistory(String history) {
        this.medicalHistory = history;
    }

  public void setStatus(String newStatus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}