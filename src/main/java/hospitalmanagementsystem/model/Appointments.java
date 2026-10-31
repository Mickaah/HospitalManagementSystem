package hospitalmanagementsystem.model;

public class Appointments implements Comparable<Appointments> {
    // The ID the database gives this appointment when it is saved (0 until then)
    private int appointmentId;
    private String patientName;
    private int patientAge;
    private String patientGender;
    private String patientId;
    private String doctorName;
    private String medicalHistory;
    private String concern;
    private String appointmentDate;
    private int urgencyLevel; // <-- This field must be declared here

    public Appointments(String patientName, int patientAge, String patientGender, 
                        String patientId, String doctorName, String medicalHistory, 
                        String concern, String appointmentDate, int urgencyLevel) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.medicalHistory = medicalHistory;
        this.concern = concern;
        this.appointmentDate = appointmentDate;
        this.urgencyLevel = urgencyLevel;
    }

    // Getters
    public int getAppointmentId() { return appointmentId; }
    public String getPatientName() { return patientName; }
    public int getPatientAge() { return patientAge; }
    public String getPatientGender() { return patientGender; }
    public String getPatientId() { return patientId; }
    public String getDoctorName() { return doctorName; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getConcern() { return concern; }
    public String getAppointmentDate() { return appointmentDate; }
    public int getUrgencyLevel() { return urgencyLevel; }

    // Setter for the database ID
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    @Override
    public int compareTo(Appointments other) {
        // Higher urgency comes first in the Priority Queue
        return Integer.compare(other.urgencyLevel, this.urgencyLevel);
    }
}