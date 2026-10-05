package hospitalmanagementsystem.model;
import java.time.LocalDateTime;

public class Appointments implements Comparable<Appointments> {
    private int appointmentId, patientId, doctorId, priority;
    private LocalDateTime date;

    public Appointments(int appointmentId, int patientId, int doctorId, LocalDateTime date, int priority) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.priority = priority; // 1 = High, 3 = Low
    }
    public Appointments() {}

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int id) { this.appointmentId = id; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int id) { this.patientId = id; }
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int id) { this.doctorId = id; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public int compareTo(Appointments o) {
        return Integer.compare(this.priority, o.priority); // Lower number = higher priority
    }

    @Override
    public String toString() {
        return "Appt #" + appointmentId + " | Patient: " + patientId + " | Doctor: " + doctorId + " | Priority: " + priority;
    }
}