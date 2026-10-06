package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Appointments;
import hospitalmanagementsystem.model.Doctor;
import hospitalmanagementsystem.model.Patient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SystemDataStructures {
    private final PatientBST patientTree;
    private final PriorityQueue<Appointments> appointmentQueue;
    private final Map<Integer, Patient> patientHashTable;
    private final Map<Integer, Doctor> doctorTable;

    /**
     * Initializes the central data structure collections:
     * - PatientBST for ordered patient tree traversal
     * - PriorityQueue for appointment triage based on urgency
     * - HashMaps for O(1) constant-time lookup of patients and doctors
     */
    public SystemDataStructures() {
        this.patientTree = new PatientBST();
        this.appointmentQueue = new PriorityQueue<>();
        this.patientHashTable = new HashMap<>();
        this.doctorTable = new HashMap<>();
    }

    /**
     * Creates and inserts a patient into both the BST and HashMap.
     * Prevents duplicate insertion if the ID already exists in the HashMap.
     */
    public void addPatient(int id, String name, int age) {
        if (verifyPatientExists(id)) return;

        Patient p = new Patient(id, name, age);
        patientTree.insert(p);
        patientHashTable.put(id, p);
    }

    /**
     * Overloaded method to insert an existing Patient object into both the BST and HashMap.
     * Verifies non-null reference and uniqueness of the patient ID before storing.
     */
    public void addPatient(Patient p) {
        if (p == null || verifyPatientExists(p.getId())) return;

        patientTree.insert(p);
        patientHashTable.put(p.getId(), p);
    }

    /**
     * Deletes a patient from both the HashMap (O(1)) and the BST (O(log n)).
     * Checks HashMap first to ensure the patient exists before attempting deletion.
     */
    public void deletePatient(int id) {
        if (!verifyPatientExists(id)) return;
        patientHashTable.remove(id);
        patientTree.remove(id);
    }

    /**
     * Checks if a patient ID exists in constant time O(1) using the HashMap key set.
     */
    public boolean verifyPatientExists(int id) {
        return patientHashTable.containsKey(id);
    }

    /**
     * Retrieves a Patient object by ID in constant time O(1) from the HashMap.
     */
    public Patient getPatient(int id) {
        return patientHashTable.get(id);
    }

    /**
     * Returns a collection view of all patients stored in the HashMap for UI binding.
     */
    public Collection<Patient> getAllPatients() {
        return patientHashTable.values();
    }

    /**
     * Creates and adds a new Doctor object into the doctor HashMap mapped by doctor ID.
     */
    public void addDoctor(int id, String name, String specialty) {
        doctorTable.put(id, new Doctor(id, name, specialty));
    }

    /**
     * Adds an existing Doctor instance directly into the doctor HashMap using its ID as key.
     */
    public void addDoctor(Doctor doc) {
        if (doc != null) {
            doctorTable.put(doc.getId(), doc);
        }
    }

    /**
     * Removes a doctor entry from the HashMap by doctor ID in O(1) time.
     */
    public void deleteDoctor(int id) {
        doctorTable.remove(id);
    }

    /**
     * Retrieves a Doctor object by ID in constant time O(1) from the HashMap.
     */
    public Doctor getDoctor(int id) {
        return doctorTable.get(id);
    }

    /**
     * Extracts all Doctor objects from the HashMap values for populating JComboBox dropdowns.
     */
    public Collection<Doctor> getAllDoctors() {
        return doctorTable.values();
    }

    /**
     * Schedules an appointment by placing it in the Max-Heap PriorityQueue based on urgency level.
     * Links the appointment ID to the patient and assigns the patient to the specified doctor.
     */
    public void scheduleAppointment(int apptId, int patientId, int doctorId, String date, int urgency) {
        if (!verifyPatientExists(patientId) || !doctorTable.containsKey(doctorId)) return;

        Appointments appt = new Appointments(apptId, patientId, doctorId, date, urgency);
        appointmentQueue.add(appt);

        patientHashTable.get(patientId).addAppointmentId(apptId);
        doctorTable.get(doctorId).assignPatient(patientId);
    }

    /**
     * Removes and returns the highest-priority appointment from the heap-based PriorityQueue in O(log n) time.
     */
    public Appointments processNextAppointment() {
        return appointmentQueue.poll();
    }

    /**
     * Scans and removes a specific appointment matching apptId from the PriorityQueue.
     */
    public boolean cancelAppointment(int apptId) {
        return appointmentQueue.removeIf(appt -> appt.getAppointmentId() == apptId);
    }

    /**
     * Returns the raw PriorityQueue instance containing all active appointments.
     */
    public PriorityQueue<Appointments> getAppointmentQueue() {
        return appointmentQueue;
    }
}