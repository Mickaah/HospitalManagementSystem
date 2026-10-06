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

    public SystemDataStructures() {
        this.patientTree = new PatientBST();
        this.appointmentQueue = new PriorityQueue<>();
        this.patientHashTable = new HashMap<>();
        this.doctorTable = new HashMap<>();
    }

    public void addPatient(int id, String name, int age) {
        if (verifyPatientExists(id)) return;

        // FIXED: Patient constructor requires 4 arguments (id, name, age, disease)
        Patient p = new Patient(id, name, age, "N/A");
        patientTree.insert(p);
        patientHashTable.put(id, p);
    }

    public void addPatient(Patient p) {
        if (p == null || verifyPatientExists(p.getId())) return;

        patientTree.insert(p);
        patientHashTable.put(p.getId(), p);
    }

    public void deletePatient(int id) {
        if (!verifyPatientExists(id)) return;
        patientHashTable.remove(id);
        patientTree.remove(id);
    }

    public boolean verifyPatientExists(int id) {
        return patientHashTable.containsKey(id);
    }

    public Patient getPatient(int id) {
        return patientHashTable.get(id);
    }

    public Collection<Patient> getAllPatients() {
        return patientHashTable.values();
    }

    public void addDoctor(int id, String name, String specialty) {
        doctorTable.put(id, new Doctor(id, name, specialty));
    }

    public void addDoctor(Doctor doc) {
        if (doc != null) {
            doctorTable.put(doc.getId(), doc);
        }
    }

    public void deleteDoctor(int id) {
        doctorTable.remove(id);
    }

    public Doctor getDoctor(int id) {
        return doctorTable.get(id);
    }

    public Collection<Doctor> getAllDoctors() {
        return doctorTable.values();
    }

    public void scheduleAppointment(int apptId, int patientId, int doctorId, String date, int urgency) {
        if (!verifyPatientExists(patientId) || !doctorTable.containsKey(doctorId)) return;

        Appointments appt = new Appointments(apptId, patientId, doctorId, date, urgency);
        appointmentQueue.add(appt);

        // Note: If addAppointmentId or assignPatient exist in your model classes, 
        // you can uncomment these lines.
        // patientHashTable.get(patientId).addAppointmentId(apptId);
        // doctorTable.get(doctorId).assignPatient(patientId);
    }

    public Appointments processNextAppointment() {
        return appointmentQueue.poll();
    }

    public boolean cancelAppointment(int apptId) {
        return appointmentQueue.removeIf(appt -> appt.getAppointmentId() == apptId);
    }

    public PriorityQueue<Appointments> getAppointmentQueue() {
        return appointmentQueue;
    }
}