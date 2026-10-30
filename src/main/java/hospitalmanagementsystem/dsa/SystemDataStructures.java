package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.database.DataBaseConnection;
import hospitalmanagementsystem.database.PatientDAO;
import hospitalmanagementsystem.model.Appointments;
import hospitalmanagementsystem.model.Doctor;
import hospitalmanagementsystem.model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SystemDataStructures {
    // The one shared copy used by every screen, so patients and appointments are kept when you switch screens
    private static SystemDataStructures instance;

    private PriorityQueue<Appointments> appointments;
    private List<Appointments> history;
    private PatientBST patientBST;

    // Classes that save and load patients and appointments in the database
    private PatientDAO patientDAO = new PatientDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public SystemDataStructures() {
        appointments = new PriorityQueue<>();
        history = new ArrayList<>();
        patientBST = new PatientBST();
    }

    // Returns the shared instance, creating it and loading the saved data the first time it is asked for
    public static synchronized SystemDataStructures getInstance() {
        if (instance == null) {
            instance = new SystemDataStructures();
            instance.loadFromDatabase();
        }
        return instance;
    }

    // Creates the tables if needed, then fills the BST, priority queue and history from the saved rows
    private void loadFromDatabase() {
        DataBaseConnection.initialize();

        for (Patient p : patientDAO.getAll()) {
            patientBST.insert(p);
        }

        for (Appointments a : appointmentDAO.getAll()) {
            appointments.offer(a);
            history.add(a);

            // Links the appointment back to its patient
            try {
                Patient p = patientBST.search(Integer.parseInt(a.getPatientId()));
                if (p != null) {
                    p.getAppointments().add(a);
                }
            } catch (NumberFormatException e) {
                System.err.println("Skipping patient link for appointment with ID " + a.getAppointmentId());
            }
        }
    }
    
    // Verify if a patient exists by ID (Hashing / BST check)
    public boolean verifyPatientExists(int patientId) {
        // If you are using your PatientBST or a hash map for verification:
        return patientBST.search(patientId) != null; 
    }

    // Finds a patient in the BST by ID; returns null if there is none
    public Patient findPatient(int patientId) {
        return patientBST.search(patientId);
    }

    // Add a new patient to the system
    public void addPatient(Patient patient) {
        if (patient != null) {
            patientBST.insert(patient);
            // Also saves the patient in the database
            patientDAO.insert(patient);
        }
    }

    // Adds appointment to the priority queue and history list
    public void addAppointment(Appointments a, Patient p, Doctor d) {
        // Saves the appointment in the database first, so it gets its database ID
        appointmentDAO.insert(a);

        appointments.offer(a);
        history.add(a);

        // Adds appointment to the patient if patient exists
        if (p != null) {
            p.getAppointments().add(a);
        }

        // Assigns patient to the doctor if doctor exists (Parsed to int to match Doctor model)
        if (d != null) {
            try {
                d.assignPatient(Integer.parseInt(a.getPatientId()));
            } catch (NumberFormatException e) {
                // Fallback if patientId contains letters; adjust Doctor.java if needed
                System.err.println("Error parsing patient ID to int: " + e.getMessage());
            }
        }
    }

    // Removes an appointment from the priority queue, the history list and the database
    public void removeAppointment(Appointments a) {
        appointments.remove(a);
        history.remove(a);
        if (a.getAppointmentId() > 0) {
            appointmentDAO.delete(a.getAppointmentId());
        }
    }

    // Gets the next highest-priority appointment
    public Appointments processNextAppointment() {
        return appointments.poll();
    }

    // Gets all appointment records
    public List<Appointments> getAppointmentHistory() {
        return history;
    }

    // Gets the appointment priority queue
    public PriorityQueue<Appointments> getAppointmentPriorityQueue() {
        return appointments;
    }
}