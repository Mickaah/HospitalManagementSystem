package hospitalmanagementsystem.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Main Landing Window for Hospital Management System
 */
public class HospitalSysApp extends JFrame implements ActionListener {

    private JLabel Title, landing;
    private JButton gtpatient, gtdoc, gtappt;

    public HospitalSysApp() {
        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen

        // Window Background Color
        getContentPane().setBackground(UITheme.LIGHT_BG);

        // Header Title
        Title = new JLabel("Welcome to Hospital Management!", SwingConstants.CENTER);
        Title.setBounds(50, 30, 500, 40);
        UIStyleUtility.styleTitleLabel(Title);
        add(Title);

        // Subtitle
        landing = new JLabel("How can we help you?", SwingConstants.CENTER);
        landing.setBounds(50, 80, 500, 30);
        landing.setFont(UITheme.SUBTITLE_FONT);
        landing.setForeground(UITheme.DARK_SLATE);
        add(landing);

        // Button 1: Book Appointment
        gtpatient = new JButton("Book Appointment (Patient)");
        gtpatient.setBounds(130, 160, 340, 60);
        UIStyleUtility.stylePrimaryButton(gtpatient);
        add(gtpatient);

        // Button 2: Staff Log in
        gtdoc = new JButton("Staff Log in (Doctor)");
        gtdoc.setBounds(130, 250, 340, 60);
        UIStyleUtility.stylePrimaryButton(gtdoc);
        add(gtdoc);

        // Button 3: See Appointment Dates
        gtappt = new JButton("See Appointment Dates");
        gtappt.setBounds(130, 340, 340, 60);
        UIStyleUtility.stylePrimaryButton(gtappt);
        gtappt.setBackground(UITheme.ACCENT_BLUE); 
        add(gtappt);

        // Event Listeners
        gtpatient.addActionListener(this);
        gtdoc.addActionListener(this);
        gtappt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gtpatient) {
            Patienttab patient = new Patienttab();
            this.setVisible(false);
            patient.setVisible(true);
        } else if (e.getSource() == gtdoc) {
            Doctortab doc = new Doctortab();
            this.setVisible(false);
            doc.setVisible(true);
        } else if (e.getSource() == gtappt) {
            Appointmenttab appt = new Appointmenttab();
            this.setVisible(false);
            appt.setVisible(true);
        }
    }

    public static void main(String[] args) {
        // Built-in modern Look and Feel without external libraries
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

        SwingUtilities.invokeLater(() -> {
            new HospitalSysApp().setVisible(true);
        });
    }
}