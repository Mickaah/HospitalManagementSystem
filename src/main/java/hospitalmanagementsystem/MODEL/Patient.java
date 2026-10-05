package hospitalmanagementsystem.model;
import hospitalmanagementsystem.MODEL.Appointments;
import java.util.ArrayList;
import java.util.List;
import hospitalmanagementsystem.GUI.HospitalSysApp;

public class Patient{
    
    private int id;
    private String name;
    private int age;
    private String gender;
    private String illness;
    private List<String> medicalHistory;
    private List<Appointments> appointments;

    public Patient(int id, String name, int age, String gender, String illness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.illness = illness;
        this.medicalHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }
    public Patient(){
    
}

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getIllness() { return illness; }
    public void setIllness(String illness) { this.illness = illness; }

    public List<String> getMedicalHistory() { return medicalHistory; }
    public void addMedicalRecord(String record) { this.medicalHistory.add(record); }

    public List<Appointments> getAppointments() { return appointments; }
    public void addAppointment(Appointments appointment) { this.appointments.add(appointment); }

    @Override
    public String toString() {
        return "Patient ID: " + id + " | Name: " + name + " | Age: " + age + " | Illness: " + illness;
    }
    
}
