package hospitalmanagementsystem.database;

import hospitalmanagementsystem.model.Appointments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Reads and writes appointment rows in the database
public class AppointmentDAO {

    // Saves an appointment and gives it the ID the database generated for it
    public boolean insert(Appointments a) {
        String sql = "INSERT INTO appointments (patient_id, patient_name, patient_age, patient_gender, "
                + "doctor_name, medical_history, concern, appointment_date, urgency_level) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, Integer.parseInt(a.getPatientId()));
            ps.setString(2, a.getPatientName());
            ps.setInt(3, a.getPatientAge());
            ps.setString(4, a.getPatientGender());
            ps.setString(5, a.getDoctorName());
            ps.setString(6, a.getMedicalHistory());
            ps.setString(7, a.getConcern());
            ps.setString(8, a.getAppointmentDate());
            ps.setInt(9, a.getUrgencyLevel());
            ps.executeUpdate();

            // Stores the generated ID on the appointment so it can be deleted later
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    a.setAppointmentId(keys.getInt(1));
                }
            }
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Could not save appointment: " + e.getMessage());
            return false;
        }
    }

    // Loads every saved appointment, oldest first
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
                        rs.getString("medical_history"),
                        rs.getString("concern"),
                        rs.getString("appointment_date"),
                        rs.getInt("urgency_level"));
                a.setAppointmentId(rs.getInt("appointment_id"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Could not load appointments: " + e.getMessage());
        }
        return list;
    }

    // Deletes an appointment by its database ID
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