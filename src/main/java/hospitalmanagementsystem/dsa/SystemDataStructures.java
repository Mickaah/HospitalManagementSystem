package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.database.DataBaseConnection;
import hospitalmanagementsystem.database.PatientDAO;
import hospitalmanagementsystem.model.Appointments;
import hospitalmanagementsystem.model.Doctor;
import hospitalmanagementsystem.model.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SystemDataStructures {

    // One shared instance of the system
    private static SystemDataStructures instance;

    // Data structures
    private PriorityQueue<Appointments> appointments;
    private List<Appointments> history;
    private PatientBST patientBST;
    private Map<Integer, Patient> patientHashMap;

    // Database classes
    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;

    // Constructor
    private SystemDataStructures() {
        appointments = new PriorityQueue<>();
        history = new ArrayList<>();
        patientBST = new PatientBST();
        patientHashMap = new HashMap<>();

        patientDAO = new PatientDAO();
        appointmentDAO = new AppointmentDAO();
    }

    // Returns the shared instance
    public static synchronized SystemDataStructures getInstance() {
        if (instance == null) {
            instance = new SystemDataStructures();
            instance.loadFromDatabase();
        }

        return instance;
    }

    // Loads patients and appointments from the database
    private void loadFromDatabase() {
        DataBaseConnection.initialize();

        // Load patients into the BST and hash map
        for (Patient patient : patientDAO.getAll()) {
            patientBST.insert(patient);
            patientHashMap.put(patient.getId(), patient);
        }

        // Load appointments into the priority queue and history
        for (Appointments appointment : appointmentDAO.getAll()) {
            appointments.offer(appointment);
            history.add(appointment);

            // Connect the appointment to its patient
            try {
                int patientId = Integer.parseInt(
                    appointment.getPatientId()
                );

                Patient patient = patientBST.search(patientId);

                if (patient != null) {
                    patient.getAppointments().add(appointment);
                }
            } catch (NumberFormatException e) {
                System.err.println(
                    "Invalid patient ID for appointment: "
                    + appointment.getAppointmentId()
                );
            }
        }
    }

    // Adds a patient to the BST and hash map, then saves it
    public void addPatient(Patient patient) {
        if (patient == null) {
            return;
        }

        int patientId = patient.getId();

        // Prevent duplicate patient IDs
        if (patientHashMap.containsKey(patientId)) {
            return;
        }

        // Save to the database first
        patientDAO.insert(patient);

        // Update memory after the database operation
        patientBST.insert(patient);
        patientHashMap.put(patientId, patient);
    }

    // Finds a patient using the BST
    public Patient findPatient(int patientId) {
        return patientBST.search(patientId);
    }

    // Verifies whether a patient exists using hashing
    public boolean verifyPatientExists(int patientId) {
        return patientHashMap.containsKey(patientId);
    }

    // Returns all patients stored in the hash map
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientHashMap.values());
    }

    // Removes a patient from the BST and hash map
    // Note: this does not delete the patient from the database.
    public void removePatientFromMemory(int patientId) {
        patientBST.remove(patientId);
        patientHashMap.remove(patientId);
    }

    // Adds an appointment and saves it to the database
    public void addAppointment(
            Appointments appointment,
            Patient patient,
            Doctor doctor) {

        if (appointment == null) {
            return;
        }

        // Save the appointment first
        appointmentDAO.insert(appointment);

        // Update the priority queue and history
        appointments.offer(appointment);
        history.add(appointment);

        // Attach appointment to the patient
        if (patient != null) {
            patient.getAppointments().add(appointment);
        }

        // Assign patient to the doctor
        if (doctor != null) {
            try {
                int patientId = Integer.parseInt(
                    appointment.getPatientId()
                );

                doctor.assignPatient(patientId);
            } catch (NumberFormatException e) {
                System.err.println(
                    "Could not assign patient to doctor: invalid patient ID."
                );
            }
        }
    }

    // Removes an appointment from the queue and database
    public void removeAppointment(Appointments appointment) {
        if (appointment == null) {
            return;
        }

        appointments.remove(appointment);
        history.remove(appointment);

        // Delete from the database if the appointment has a valid ID
        if (appointment.getAppointmentId() > 0) {
            appointmentDAO.delete(appointment.getAppointmentId());
        }
    }

    // Returns the next appointment with the highest priority
    public Appointments processNextAppointment() {
        return appointments.poll();
    }

    // Returns a copy of the appointment history
    public List<Appointments> getAppointmentHistory() {
        return new ArrayList<>(history);
    }

    // Returns a copy of the current appointment queue
    public PriorityQueue<Appointments> getAppointmentPriorityQueue() {
        return new PriorityQueue<>(appointments);
    }
}