package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.dsa.SystemDataStructures;
import hospitalmanagementsystem.model.Appointments;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    // Shared appointment system
    private static AppointmentManager instance;

    private SystemDataStructures system;

    // Constructor
    private AppointmentManager() {
        system = SystemDataStructures.getInstance();
    }

    // Returns the shared AppointmentManager
    public static synchronized AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }

        return instance;
    }

    // Adds an appointment to the shared system
    public void addAppointment(Appointments appointment) {
        if (appointment != null) {
            system.addAppointment(appointment, null, null);
        }
    }

    // Removes an appointment from the shared system
    public void removeAppointment(Appointments appointment) {
        if (appointment != null) {
            system.removeAppointment(appointment);
        }
    }

    // Gets all appointments for display and reporting
    public List<Appointments> getAllAppointments() {
        return system.getAppointmentHistory();
    }

    // Searches appointments by patient name, patient ID, or doctor name
    public List<Appointments> searchAppointments(String query) {
        List<Appointments> results = new ArrayList<>();

        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        query = query.toLowerCase().trim();

        for (Appointments appointment : getAllAppointments()) {

            String patientName = appointment.getPatientName();
            String patientId = appointment.getPatientId();
            String doctorName = appointment.getDoctorName();

            if ((patientName != null
                    && patientName.toLowerCase().contains(query))
                || (patientId != null
                    && patientId.toLowerCase().contains(query))
                || (doctorName != null
                    && doctorName.toLowerCase().contains(query))) {

                results.add(appointment);
            }
        }

        return results;
    }
}