package HospitalSys;

import hospitalmanagementsystem.GUI.HospitalSysApp;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main Launcher File for the Hospital Management System
 */
public class HospitalSys {

    public static void main(String[] args) {
        // Set built-in modern Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Could not initialize Look and Feel.");
        }

        // Launch the main GUI application window
        SwingUtilities.invokeLater(() -> {
            new HospitalSysApp().setVisible(true);
        });
    }
}