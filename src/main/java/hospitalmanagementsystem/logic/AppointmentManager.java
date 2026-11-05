package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.model.Appointments;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AppointmentManager {
    private static AppointmentManager instance;
    private PriorityQueue<Appointments> appointmentQueue;
    private List<Appointments> appointmentHistory;

    // Reads the number at the start of the urgency text (e.g. 4 from "4 - Critical"); returns 0 if there is none
    private static int urgencyNumber(Appointments a) {
        String text = a.getUrgencyLevel();
        if (text == null) {
            return 0;
        }
        text = text.trim();
        int end = 0;
        while (end < text.length() && Character.isDigit(text.charAt(end))) {
            end++;
        }
        if (end == 0) {
            return 0;
        }
        return Integer.parseInt(text.substring(0, end));
    }

    // Orders appointments by urgency number (highest first), then by appointment ID (lowest first)
    private static final Comparator<Appointments> URGENCY_THEN_ID = new Comparator<Appointments>() {
        @Override
        public int compare(Appointments a, Appointments b) {
            int byUrgency = Integer.compare(urgencyNumber(b), urgencyNumber(a));
            if (byUrgency != 0) {
                return byUrgency;
            }
            return Integer.compare(a.getAppointmentId(), b.getAppointmentId());
        }
    };

    private AppointmentManager() {
        // The queue uses the urgency-then-ID order above, so the next patient processed matches the top row of the table:
        appointmentQueue = new PriorityQueue<>(URGENCY_THEN_ID);
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

    // Automatically generate the next appointment ID
    public int generateNextAppointmentId() {
        if (appointmentHistory == null || appointmentHistory.isEmpty()) {
            return 1; // Start from ID 1 if empty
        }
        int maxId = 0;
        for (Appointments a : appointmentHistory) {
            if (a.getAppointmentId() > maxId) {
                maxId = a.getAppointmentId();
            }
        }
        return maxId + 1;
    }

    // Get the next most urgent appointment from the queue (e.g. for doctor consultation)
    public Appointments getNextUrgentAppointment() {
        return appointmentQueue.poll();
    }

    // Peek at the next urgent appointment without removing it from the queue
    public Appointments peekNextUrgentAppointment() {
        return appointmentQueue.peek();
    }

    public List<Appointments> getAllAppointments() {
        return appointmentHistory;
    }

    public List<Appointments> searchAppointments(String query) {
        List<Appointments> results = new ArrayList<>();
        for (Appointments appt : appointmentHistory) {
            if (appt.getPatientName().toLowerCase().contains(query.toLowerCase()) ||
                appt.getPatientId().toLowerCase().contains(query.toLowerCase()) ||
                appt.getDoctorName().toLowerCase().contains(query.toLowerCase())) {
                results.add(appt);
            }
        }
        return results;
    }

    // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    // Returns a copy of the queue sorted by urgency number (highest first), then appointment ID (a PriorityQueue's iterator is not in priority order)
    public List<Appointments> getPriorityQueueAppointments() {
        List<Appointments> sorted = new ArrayList<>(appointmentQueue);
        sorted.sort(URGENCY_THEN_ID);
        return sorted;
    }

    // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    // Removes and returns the most urgent appointment, or null when the queue is empty (the history list keeps it so IDs are not reused)
    public Appointments processNextAppointment() {
        return appointmentQueue.poll();
    }

    // Loads the appointments saved in MySQL into the queue and history, skipping any ID that is already loaded
    public void loadFromDatabase() {
        List<Appointments> saved = new AppointmentDAO().getAll();
        for (Appointments dbAppt : saved) {
            boolean alreadyLoaded = false;
            for (Appointments existing : appointmentHistory) {
                if (existing.getAppointmentId() == dbAppt.getAppointmentId()) {
                    alreadyLoaded = true;
                    break;
                }
            }
            if (!alreadyLoaded) {
                addAppointment(dbAppt);
            }
        }
    }

    // Lowercases a doctor name and strips the "Dr." prefix and any bracketed specialty, e.g. "Dr. Almazan (General Medicine)" becomes "almazan"
    private static String cleanDoctorName(String name) {
        if (name == null) {
            return "";
        }
        String cleaned = name.toLowerCase().replaceAll("\\(.*?\\)", "").trim();
        cleaned = cleaned.replaceAll("^dr\\.\\s*|^dr\\s+", "");
        return cleaned.trim();
    }

    // Checks whether the doctor text saved on an appointment belongs to the given doctor name (names only, so doctors sharing a specialty stay separate)
    public static boolean doctorMatches(String bookedDoctorText, String doctorName) {
        String booked = cleanDoctorName(bookedDoctorText);
        String logged = cleanDoctorName(doctorName);
        if (booked.isEmpty() || logged.isEmpty()) {
            return false;
        }
        return booked.equals(logged) || booked.contains(logged) || logged.contains(booked);
    }

    // Returns the waiting appointments booked under the given doctor, in urgency-then-ID order
    public List<Appointments> getAppointmentsForDoctor(String doctorName) {
        List<Appointments> results = new ArrayList<>();
        for (Appointments appt : getPriorityQueueAppointments()) {
            if (doctorMatches(appt.getDoctorName(), doctorName)) {
                results.add(appt);
            }
        }
        return results;
    }
}