package hospitalmanagementsystem.database;

import hospitalmanagementsystem.model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Reads and writes patient rows in the database
public class PatientDAO {

    // Saves a patient; returns false if it could not be saved (for example, the ID already exists)
    public boolean insert(Patient p) {
        String sql = "INSERT INTO patients (id, name, age, gender, medical_history) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getMedicalHistory());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Could not save patient: " + e.getMessage());
            return false;
        }
    }

    // Loads every saved patient
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT id, name, age, gender, medical_history FROM patients";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("medical_history")));
            }
        } catch (SQLException e) {
            System.err.println("Could not load patients: " + e.getMessage());
        }
        return patients;
    }

    // Deletes a patient by ID
    public boolean delete(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Could not delete patient: " + e.getMessage());
            return false;
        }
    }
}