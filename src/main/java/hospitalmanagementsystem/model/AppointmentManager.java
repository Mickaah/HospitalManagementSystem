package hospitalmanagementsystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppointmentManager {
    
    private List<Appointments> appointmentList;

    public AppointmentManager() {
        this.appointmentList = new ArrayList<>();
    }

    // Schedule a new appointment and keep the list sorted by urgency
    public void scheduleAppointment(Appointments appointment) {
        if (appointment != null) {
            appointmentList.add(appointment);
            // Sorts automatically using your Comparable implementation in Appointments
            Collections.sort(appointmentList);
            System.out.println("Appointment Scheduled Successfully (ID: " + appointment.getAppointmentId() + ")");
        }
    }

    // Get the most urgent appointment (the first one in the sorted list)
    public Appointments getNextUrgentAppointment() {
        if (appointmentList.isEmpty()) {
            return null;
        }
        return appointmentList.get(0);
    }

    // Find all appointments for a specific Patient ID
    public List<Appointments> getAppointmentsByPatient(int patientId) {
        List<Appointments> result = new ArrayList<>();
        for (Appointments appt : appointmentList) {
            if (appt.getPatientId() == patientId) {
                result.add(appt);
            }
        }
        return result;
    }

    // Find all appointments assigned to a specific Doctor ID
    public List<Appointments> getAppointmentsByDoctor(int doctorId) {
        List<Appointments> result = new ArrayList<>();
        for (Appointments appt : appointmentList) {
            if (appt.getDoctorId() == doctorId) {
                result.add(appt);
            }
        }
        return result;
    }

    // Cancel / remove an appointment by its ID
    public boolean cancelAppointment(int appointmentId) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getAppointmentId() == appointmentId) {
                appointmentList.remove(i);
                System.out.println("Appointment ID " + appointmentId + " has been cancelled.");
                return true;
            }
        }
        System.out.println("Appointment ID " + appointmentId + " not found.");
        return false;
    }

    // Return the full list of appointments (sorted by urgency)
    public List<Appointments> getAllAppointments() {
        return appointmentList;
    }

    // Display all scheduled appointments
    public void displayAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments currently scheduled.");
            return;
        }
        
        System.out.println("--- Scheduled Appointments (Ordered by Urgency) ---");
        for (Appointments appt : appointmentList) {
            System.out.println("Appt ID: " + appt.getAppointmentId() + 
                               " | Patient ID: " + appt.getPatientId() + 
                               " | Doctor ID: " + appt.getDoctorId() + 
                               " | Date: " + appt.getDate() + 
                               " | Urgency: " + appt.getUrgency());
        }
    }
}