package hospitalmanagementsystem.database;

import hospitalmanagementsystem.model.Appointments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Returns the appointment's specialty, or reads it from the brackets in the doctor name (e.g. "Dr. Almazan (General Medicine)") when it is empty
    public static String resolveSpecialty(Appointments a) {
        String specialty = a.getSpecialty();
        if (specialty != null && !specialty.trim().isEmpty()) {
            return specialty.trim();
        }
        String doctor = a.getDoctorName();
        if (doctor != null) {
            int open = doctor.lastIndexOf('(');
            int close = doctor.lastIndexOf(')');
            if (open >= 0 && close > open) {
                return doctor.substring(open + 1, close).trim();
            }
        }
        return "";
    }

    public boolean insert(Appointments a) {
        String sql = "INSERT INTO appointments (patient_id, patient_name, patient_age, patient_gender, "
                + "doctor_name, specialty, medical_history, concern, appointment_date, urgency_level) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            int patId = 0;
            try {
                patId = Integer.parseInt(a.getPatientId());
            } catch (Exception e) {
                patId = 0;
            }

            ps.setInt(1, patId);
            ps.setString(2, a.getPatientName());
            ps.setInt(3, a.getPatientAge());
            ps.setString(4, a.getPatientGender());
            ps.setString(5, a.getDoctorName());
            ps.setString(6, resolveSpecialty(a)); // saves the specialty, filled in from the doctor name when it was left empty
            ps.setString(7, a.getMedicalHistory());
            ps.setString(8, a.getConcern());
            ps.setString(9, a.getAppointmentDate());
            ps.setString(10, a.getUrgencyLevel()); // CHANGED from setInt to setString to store text like "2 - Medium"
            
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    a.setAppointmentId(keys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Could not save appointment: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }

    public List<Appointments> getAll() {
        List<Appointments> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_id";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Appointments a = new Appointments(
                        rs.getString("patient_name"),
                        rs.getInt("patient_age"),
                        rs.getString("patient_gender"),
                        String.valueOf(rs.getInt("patient_id")),
                        rs.getString("doctor_name"),
                        rs.getString("specialty"),
                        rs.getString("medical_history"),
                        rs.getString("concern"),
                        rs.getString("appointment_date"),
                        rs.getString("urgency_level") // CHANGED from getInt to getString to load text like "2 - Medium"
                );
                a.setAppointmentId(rs.getInt("appointment_id"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Could not load appointments: " + e.getMessage());
        }
        return list;
    }

    public boolean delete(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Could not delete appointment: " + e.getMessage());
            return false;
        }
    }
}