package hospitalmanagementsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Opens connections to the XAMPP MySQL database and creates tables if they don't exist
public class DataBaseConnection {
    
    // Update "hospital_db" to match the actual database name you created in XAMPP phpMyAdmin
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Default XAMPP username
    private static final String PASSWORD = ""; // Default XAMPP password is usually blank

    // Returns a new connection to the XAMPP MySQL database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Creates the patients and appointments tables if they don't exist yet (MySQL syntax)
    public static void initialize() {
        String patients = "CREATE TABLE IF NOT EXISTS patients ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "age INT, "
                + "gender VARCHAR(20), "
                + "medical_history TEXT)";

        String appointments = "CREATE TABLE IF NOT EXISTS appointments ("
                + "appointment_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "patient_id INT NOT NULL, "
                + "patient_name VARCHAR(100), "
                + "patient_age INT, "
                + "patient_gender VARCHAR(20), "
                + "doctor_name VARCHAR(100), "
                + "medical_history TEXT, "
                + "concern TEXT, "
                + "appointment_date VARCHAR(50), "
                + "urgency_level INT)";

        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(patients);
            st.execute(appointments);
            System.out.println("Database connected and tables initialized successfully!");
        } catch (SQLException e) {
            System.err.println("Could not set up the database: " + e.getMessage());
        }
    }
}