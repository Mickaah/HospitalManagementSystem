package hospitalmanagementsystem.model;

public class Appointments implements Comparable<Appointments> {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String date;
    private int urgency; // Higher value = Higher priority (1-5)

    public Appointments(int appointmentId, int patientId, int doctorId, String date, int urgency) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.urgency = urgency;
    }

    public int getAppointmentId() { return appointmentId; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public int getUrgency() { return urgency; }

    @Override
    public int compareTo(Appointments other) {
        // High urgency comes first in PriorityQueue
        return Integer.compare(other.urgency, this.urgency);
    }
}