package hospitalmanagementsystem.model;

public class Appointments implements Comparable<Appointments> {
    private int appointmentId, patientId, doctorId, urgency;
    private String date;

    // Creates a new appointment
    public Appointments(int appointmentId, int patientId, int doctorId,
                        String date, int urgency) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.urgency = urgency;
    }

    // Gets appointment information
    public int getAppointmentId() { return appointmentId; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public int getUrgency() { return urgency; }

    // Sorts appointments by urgency
    public int compareTo(Appointments a) {
        return Integer.compare(a.urgency, urgency);
    }
}
