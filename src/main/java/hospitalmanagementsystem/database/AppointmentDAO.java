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
            ps.setString(6, a.getSpecialty());
            ps.setString(7, a.getMedicalHistory());
            ps.setString(8, a.getConcern());
            ps.setString(9, a.getAppointmentDate());
            ps.setInt(10, a.getUrgencyLevel());
            
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    a.setAppointmentId(keys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Could not save appointment: " + e.getMessage());
            e.printStackTrace(); // Prints the full error stack trace to NetBeans output
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
                        rs.getInt("urgency_level")
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