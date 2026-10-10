package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.dsa.PatientBST;
import hospitalmanagementsystem.model.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientManager {
    
    private PatientBST patientBST;

    public PatientManager() {
        this.patientBST = new PatientBST();
    }

    // Add a patient
    public void addPatient(Patient patient) {
        if (patient != null) {
            patientBST.insert(patient);
            System.out.println("Patient added successfully by the Patient Manager.");
        }
    }

    // Search for a patient by their ID
    public Patient searchPatient(int id) {
        return patientBST.search(id);
    }

    // Remove a patient by ID
    public void removePatient(int id) {
        Patient found = patientBST.search(id);
        if (found != null) {
            patientBST.remove(id);
            System.out.println("Patient with ID " + id + " has been discharged/removed.");
        } else {
            System.out.println("Patient with ID " + id + " not found.");
        }
    }

    // Update patient information (e.g., status or room)
    public boolean updatePatientStatus(int id, String newStatus) {
        Patient patient = patientBST.search(id);
        if (patient != null) {
            // Assuming Patient has a setStatus method
            patient.setStatus(newStatus);
            System.out.println("Patient ID " + id + " status updated to: " + newStatus);
            return true;
        }
        System.out.println("Patient not found for update.");
        return false;
    }

    // Get the underlying tree if needed
    public PatientBST getPatientBST() {
        return patientBST;
    }
}