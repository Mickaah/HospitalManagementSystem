package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.*;
import java.util.*;

public class SystemDataStructures {

    // Stores patients using BST and HashMap
    private PatientBST patientTree = new PatientBST();
    private Map<Integer, Patient> patients = new HashMap<>();

    // Stores doctors using HashMap
    private Map<Integer, Doctor> doctors = new HashMap<>();

    // Stores appointments based on urgency
    private PriorityQueue<Appointments> appointments = new PriorityQueue<>();

    // Stores all appointment records
    private List<Appointments> history = new ArrayList<>();
    
    // Adds a patient to the system
    public void addPatient(Patient p) {
        patientTree.insert(p);
        patients.put(p.getId(), p);
    }
    // Finds a patient using their ID
    public Patient getPatient(int id) {
        return patients.get(id);
    }
    // Checks if a patient exists
    public boolean verifyPatientExists(int id) {
        return patients.containsKey(id);
    }
    // Removes a patient from the system
    public void removePatient(int id) {
        patientTree.remove(id);
        patients.remove(id);
    }
    // Gets all patients
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }
    // Adds a doctor to the system
    public void addDoctor(Doctor d) {
        doctors.put(d.getId(), d);
    }
    // Finds a doctor using their ID
    public Doctor getDoctor(int id) {
        return doctors.get(id);
    }
    // Gets all doctors for the GUI
    public List<Doctor> getDoctorList() {
        return new ArrayList<>(doctors.values());
    }
    // Creates and saves an appointment
    public void scheduleAppointment(Appointments a) {
        appointments.add(a);
        history.add(a);

        Patient p = patients.get(a.getPatientId());
        Doctor d = doctors.get(a.getDoctorId());

        // Adds appointment to the patient
        if (p != null)
            p.getAppointments().add(a);

        // Assigns patient to the doctor
        if (d != null)
            d.assignPatient(a.getPatientId());
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