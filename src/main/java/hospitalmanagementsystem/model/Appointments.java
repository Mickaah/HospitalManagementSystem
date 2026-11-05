package hospitalmanagementsystem.model;

public class Appointments {
    private int appointmentId;
    private String patientId;
    private String patientName;
    private int patientAge;
    private String patientGender;
    private String doctorName;
    private String specialty;
    private String medicalHistory;
    private String concern;
    private String appointmentDate;
    private int urgencyLevel;

    // Constructor
    public Appointments(String patientName, int patientAge, String patientGender, String patientId, 
                        String doctorName, String specialty, String medicalHistory, String concern, 
                        String appointmentDate, int urgencyLevel) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.medicalHistory = medicalHistory;
        this.concern = concern;
        this.appointmentDate = appointmentDate;
        this.urgencyLevel = urgencyLevel;
    }

    // Getters and Setters
    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public int getPatientAge() { return patientAge; }
    public String getPatientGender() { return patientGender; }
    public String getDoctorName() { return doctorName; }
    public String getSpecialty() { return specialty; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getConcern() { return concern; }
    public String getAppointmentDate() { return appointmentDate; }
    public int getUrgencyLevel() { return urgencyLevel; }
}