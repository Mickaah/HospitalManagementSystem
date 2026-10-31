package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    private static DoctorManager instance;
    private List<Doctor> doctorList;

    private DoctorManager() {
        this.doctorList = new ArrayList<>();
        
        // Add default sample doctors so your ComboBox isn't empty on start
        // (Adjust constructor arguments based on your Doctor model fields, e.g., ID, Name, Specialty)
        // Each sample doctor also has a username and password for the Doctor Log-In screen
        doctorList.add(new Doctor(1, "Tambis", "Cardiology", "tambis", "tambis123"));
        doctorList.add(new Doctor(2, "Tumampo", "Pediatrics", "tumampo", "tumampo123"));
        doctorList.add(new Doctor(3, "Almazan", "General Medicine", "almazan", "almazan123"));
        doctorList.add(new Doctor(4, "Magpili", "Dermatologist", "magpili", "magpili123"));
        doctorList.add(new Doctor(5, "Guce", "Psychiatrists", "guce", "guce123"));
    }

    public static synchronized DoctorManager getInstance() {
        if (instance == null) {
            instance = new DoctorManager();
        }
        return instance;
    }

    // Add a new doctor 
    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctorList.add(doctor);
            System.out.println("Doctor Registered Successfully: " + doctor);
        }
    }

    // Checks the username and password against the registered doctors;
    // returns the matching doctor, or null if the login is wrong
    public Doctor login(String username, String password) {
        for (Doctor doc : doctorList) {
            if (doc.checkLogin(username, password)) {
                return doc;
            }
        }
        return null;
    }

    // Search for a doctor by their ID
    public Doctor searchDoctor(int id) {
        for (Doctor doc : doctorList) {
            if (doc.getId() == id) {
                return doc;
            }
        }
        return null;
    }

    // Assign a patient ID to a specific doctor
    public boolean assignPatientToDoctor(int doctorId, int patientId) {
        Doctor doctor = searchDoctor(doctorId);
        if (doctor != null) {
            doctor.assignPatient(patientId);
            System.out.println("Patient ID " + patientId + " successfully assigned to " + doctor);
            return true;
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return false;
    }

    // Find doctors by specialty (e.g., "Cardiology", "Neurology")
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        List<Doctor> matchedDoctors = new ArrayList<>();
        for (Doctor doc : doctorList) {
            if (doc.getSpecialty().equalsIgnoreCase(specialty)) {
                matchedDoctors.add(doc);
            }
        }
        return matchedDoctors;
    }

    // Remove a doctor by ID
    public boolean removeDoctor(int id) {
        Doctor doctor = searchDoctor(id);
        if (doctor != null) {
            doctorList.remove(doctor);
            System.out.println("Doctor with ID " + id + " has been removed from the system.");
            return true;
        }
        System.out.println("Doctor ID " + id + " not found.");
        return false;
    }

    // Return the full list of doctors
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }

    // Display all registered doctors
    public void displayAllDoctors() {
        if (doctorList.isEmpty()) {
            System.out.println("No doctors currently registered in the system.");
            return;
        }
        
        System.out.println("--- Hospital Doctor Directory ---");
        for (Doctor doc : doctorList) {
            System.out.println("ID: " + doc.getId() + " | " + doc + " | Patients Assigned: " + doc.getAssignedPatientIds().size());
        }
    }
}