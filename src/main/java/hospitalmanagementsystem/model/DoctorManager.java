package hospitalmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    
    private List<Doctor> doctorList;

    public DoctorManager() {
        this.doctorList = new ArrayList<>();
    }

    // Add a new doctor 
    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctorList.add(doctor);
            System.out.println("Doctor Registered Successfully: " + doctor);
        }
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