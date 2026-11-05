package hospitalmanagementsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/doctor_accs?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Returns a new connection to the XAMPP MySQL database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {
        // Added AUTO_INCREMENT to patients table so IDs don't conflict/duplicate
        String patients = "CREATE TABLE IF NOT EXISTS patients ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "age INT, "
                + "gender VARCHAR(20), "
                + "medical_history TEXT)";

        // Changed urgency_level from INT to VARCHAR(50) to support strings like "4 - Critical"
        String appointments = "CREATE TABLE IF NOT EXISTS appointments ("
                + "appointment_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "patient_id INT NOT NULL, "
                + "patient_name VARCHAR(100), "
                + "patient_age INT, "
                + "patient_gender VARCHAR(20), "
                + "doctor_name VARCHAR(100), "
                + "specialty VARCHAR(100), " 
                + "medical_history TEXT, "
                + "concern TEXT, "
                + "appointment_date VARCHAR(50), "
                + "urgency_level VARCHAR(50))";

        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(patients);
            st.execute(appointments);
            System.out.println("Database connected and tables initialized successfully!");
        } catch (SQLException e) {
            System.err.println("Could not set up the database: " + e.getMessage());
        }
    }
}