package hospitalmanagementsystem.model;

public class Appointments {
    private int id;
    private int patientId;
    private int doctorId;
    private String date;
    private String time;
    private int urgencyLevel; // e.g., 1 (Low) to 5 (Critical)

    public Appointments(int id, int patientId, int doctorId, String date, String time, int urgencyLevel) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.urgencyLevel = urgencyLevel;
    }

    // Getter required for PriorityQueue in SystemDataStructures.java
    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}