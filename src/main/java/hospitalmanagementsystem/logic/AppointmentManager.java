package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.model.Appointments;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AppointmentManager {
    private static AppointmentManager instance;
    private PriorityQueue<Appointments> appointmentQueue;
    private List<Appointments> appointmentHistory;

    private AppointmentManager() {
        appointmentQueue = new PriorityQueue<>();
        appointmentHistory = new ArrayList<>();
    }

    public static synchronized AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    public void addAppointment(Appointments appointment) {
        appointmentQueue.offer(appointment);
        appointmentHistory.add(appointment);
    }

    public void removeAppointment(Appointments appointment) {
        appointmentQueue.remove(appointment);
        appointmentHistory.remove(appointment);
    }

    public List<Appointments> getAllAppointments() {
        return appointmentHistory;
    }

    public List<Appointments> searchAppointments(String query) {
        List<Appointments> results = new ArrayList<>();
        for (Appointments appt : appointmentHistory) {
            if (appt.getPatientName().toLowerCase().contains(query.toLowerCase()) ||
                appt.getPatientId().toLowerCase().contains(query.toLowerCase()) ||
                appt.getDoctorName().toLowerCase().contains(query.toLowerCase())) { // Fixed: getDoctorName with capital D
                results.add(appt);
            }
        }
        return results;
    }
}