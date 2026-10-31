/*
 * Description: A system for managing patient records, doctor appointments, and treatment history in hospitals.
Key Features:
Add, update, delete, and search for patients and doctors.
Schedule and manage appointments.
Generate reports on patient visits and treatments.
Class Structure:
Patient: Properties include id, name, age, medicalHistory, and appointments.
Doctor: Properties include id, name, specialty, and patients.
Appointment: Properties include appointmentId, patientId, doctorId, and date.
Data Structures/Algorithms:
Use a binary search tree for organizing patients by ID.
Implement priority queues for managing appointment scheduling based on urgency.
Use hashing for quick patient verification.

reference list:
DoA = Date of Appointment
 */

package hospitalmanagementsystem.GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Mikeyks
 */
public class HospitalSysApp extends JFrame implements ActionListener{
    private JLabel Title, landing;
    // Logo drawn at the top of the menu (defined in the LogoPanel class)
    private LogoPanel logoPanel;
   private JButton gtpatient, gtdoc, gtappt;
   private JPanel backgroundPanel;
    
    public HospitalSysApp () {
        setTitle("De Senta Ambroccio Hospital App");
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        setVisible(true);
        this.setLocationRelativeTo(this);
        
        // Creates the 100x100 drawn logo and places it centered at the top of the menu
        logoPanel = new LogoPanel(100);
        logoPanel.setBounds(240, 10, 100, 100);
        add(logoPanel);
        
        // Uses the same drawn logo as the window's title bar icon
        setIconImage(LogoPanel.getLogoImage(64));
        
        Title = new JLabel("""
                           Welcome to De Santa Ambroccio
                            Hospital!""");
        Title.setBounds(107, 105, 400, 100);
        Title.setFont(new Font("Western", Font.BOLD, 20));
        add(Title);
        
        landing = new JLabel("How can we help you?");
        landing.setBounds(185, 180, 280, 80);
        landing.setFont(new Font("Western", Font.PLAIN, 20));
        add(landing);
        
        gtpatient = new JButton("Book Appointment (Patient)");
        gtpatient.setBounds(130, 280, 320, 70);
        gtpatient.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtpatient);
        
        gtdoc= new JButton("Staff Log in (Doctor)");
        gtdoc.setBounds(130, 370, 320, 70);
        gtdoc.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtdoc);
        
        gtappt = new JButton("See Appointment Dates");
        gtappt.setBounds(130, 460, 320, 70);
        gtappt.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtappt);
        
        gtpatient.addActionListener(this);
        gtdoc.addActionListener(this);
        gtappt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gtpatient) {
            hospitalmanagementsystem.GUI.Patienttab patient = new hospitalmanagementsystem.GUI.Patienttab();
            this.setVisible(false);
            patient.setVisible(true);
        }
        else if (e.getSource() == gtdoc){
            hospitalmanagementsystem.GUI.Doctortab doc = new hospitalmanagementsystem.GUI.Doctortab();
            this.setVisible(false);
            doc.setVisible(true);
        }
        else if (e.getSource() == gtappt) {
            hospitalmanagementsystem.GUI.Appointmenttab appt = new hospitalmanagementsystem.GUI.Appointmenttab();
            this.setVisible(false);
            appt.setVisible(true);
        }
    }
}