package hospitalmanagementsystem.GUI;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.database.PatientDAO;
import hospitalmanagementsystem.dsa.SystemDataStructures;
import hospitalmanagementsystem.logic.AppointmentManager;
import hospitalmanagementsystem.logic.DoctorManager;
import hospitalmanagementsystem.model.Appointments;
import hospitalmanagementsystem.model.Doctor;
import hospitalmanagementsystem.model.Patient;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mikeyks
 */
public class Patienttab extends JFrame implements ActionListener {
    private JLabel txtTitle, patname, patage, patgenlab, patillness, medhist, apptsched, docappt, urgencyLab;
    private JComboBox<String> patgender, avaibdocs, urgencyComboBox;
    private JTextField patnameF, patageF, apptschedF;
    private JTextArea patillnessF, medhistF; 
    private DefaultTableModel patres;
    private JTable tab; 
    private JButton btnBack, btnAdd, btnRemove;
    private JPanel backgroundPanel;
    private ImageIcon image;
    private String[] gend = {"male", "female", "trans(male/female)"}; 
    private String[] avaib = {};
    private String[] urgencyLevels = {"1 - Low", "2 - Medium", "3 - High", "4 - Critical"};
    
    private SystemDataStructures systemDataStructures;

    public Patienttab() {
        this(new SystemDataStructures());
    }

    public Patienttab(SystemDataStructures sharedDataStructures) {
        this.systemDataStructures = (sharedDataStructures != null) ? sharedDataStructures : new SystemDataStructures();

        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(875, 520);
        setLayout(null);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        image = new ImageIcon("src/main/java/hospitalmanagementsystem/GUI/Logo.png");
        Image scaledImage = image.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        setIconImage(scaledImage);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        txtTitle = new JLabel("Book an Appointment");
        txtTitle.setBounds(20, 10, 300, 30);
        txtTitle.setFont(new Font("Western", Font.BOLD, 18));
        add(txtTitle);
        
        // --- COLUMN 1 (Left Side: X = 20) ---
        
        // Patient Name
        patname = new JLabel("Patient's Name:");
        patname.setBounds(20, 50, 130, 25);
        patname.setFont(new Font("Western", Font.BOLD, 13));
        add(patname);
        
        patnameF = new JTextField();
        patnameF.setBounds(150, 50, 240, 25);
        patnameF.setFont(new Font("Western", Font.PLAIN, 13));
        add(patnameF);
        
        // Patient Age
        patage = new JLabel("Patient's Age:");
        patage.setBounds(20, 85, 130, 25);
        patage.setFont(new Font("Western", Font.BOLD, 13));
        add(patage);
        
        patageF = new JTextField();
        patageF.setBounds(150, 85, 240, 25);
        patageF.setFont(new Font("Western", Font.PLAIN, 13));
        add(patageF);
        
        // Patient Gender
        patgenlab = new JLabel("Patient's Gender:");
        patgenlab.setBounds(20, 120, 130, 25);
        patgenlab.setFont(new Font("Western", Font.BOLD, 13));
        add(patgenlab);
        
        patgender = new JComboBox<>(gend);
        patgender.setBounds(150, 120, 240, 25);
        patgender.setFont(new Font("Western", Font.PLAIN, 13));
        add(patgender);
        
        // --- COLUMN 2 (Right Side: X = 430) ---
        
        // Doctor Selection
        docappt = new JLabel("See Doctor:");
        docappt.setBounds(430, 50, 110, 25);
        docappt.setFont(new Font("Western", Font.BOLD, 13));
        add(docappt);
        
        avaibdocs = new JComboBox<>(avaib);
        avaibdocs.setBounds(530, 50, 295, 25);
        avaibdocs.setFont(new Font("Western", Font.PLAIN, 13));
        add(avaibdocs);

        // Urgency Level
        urgencyLab = new JLabel("Urgency Level:");
        urgencyLab.setBounds(430, 85, 110, 25);
        urgencyLab.setFont(new Font("Western", Font.BOLD, 13));
        add(urgencyLab);

        urgencyComboBox = new JComboBox<>(urgencyLevels);
        urgencyComboBox.setBounds(530, 85, 295, 25);
        urgencyComboBox.setFont(new Font("Western", Font.PLAIN, 13));
        add(urgencyComboBox);

        // Appointment Date
        apptsched = new JLabel("Appointment Date:");
        apptsched.setBounds(430, 120, 120, 25);
        apptsched.setFont(new Font("Western", Font.BOLD, 13));
        add(apptsched);
        
        apptschedF = new JTextField();
        apptschedF.setBounds(560, 120, 265, 25);
        apptschedF.setFont(new Font("Western", Font.PLAIN, 13));
        add(apptschedF);

        // --- ROW 3 (Text Areas for History & Concern) ---
        
        // Medical History
        medhist = new JLabel("Previous Medical History:");
        medhist.setBounds(20, 160, 180, 20);
        medhist.setFont(new Font("Western", Font.BOLD, 13));
        add(medhist);
        
        medhistF = new JTextArea();
        medhistF.setLineWrap(true);
        medhistF.setWrapStyleWord(true);
        JScrollPane scrollMed = new JScrollPane(medhistF);
        scrollMed.setBounds(20, 182, 395, 50);
        add(scrollMed);
        
        // Concern / Illness
        patillness = new JLabel("Patient Concern / Illness:");
        patillness.setBounds(430, 160, 180, 20);
        patillness.setFont(new Font("Western", Font.BOLD, 13));
        add(patillness);
        
        patillnessF = new JTextArea();
        patillnessF.setLineWrap(true);
        patillnessF.setWrapStyleWord(true);
        JScrollPane scrollConcern = new JScrollPane(patillnessF);
        scrollConcern.setBounds(430, 182, 395, 50);
        add(scrollConcern);
        
        loadDoctorsIntoComboBox();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                loadDoctorsIntoComboBox();
            }
        });
        
        // --- TABLE SETUP ---
        patres = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patres.addColumn("Patient Name");
        patres.addColumn("Age");
        patres.addColumn("Gender");
        patres.addColumn("Date");
        patres.addColumn("Urgency");
        patres.addColumn("Doctor");
        
        tab = new JTable(patres);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.getTableHeader().setResizingAllowed(false);
        tab.getTableHeader().setBackground(Color.decode("#bdfdc5"));
        tab.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane tableScroll = new JScrollPane(tab);
        tableScroll.setBounds(20, 245, 805, 175);
        add(tableScroll);
                
        // --- ACTION BUTTONS ---
        btnAdd = new JButton("Add");
        btnAdd.setBounds(540, 430, 90, 35);
        add(btnAdd); 
        
        btnRemove = new JButton("Remove");
        btnRemove.setBounds(640, 430, 90, 35);
        add(btnRemove); 

        btnBack = new JButton("Back");
        btnBack.setBounds(740, 430, 85, 35);
        add(btnBack); 
        
        btnBack.addActionListener(this);
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
    }
    
    private void loadDoctorsIntoComboBox() {
        Object previousSelection = avaibdocs.getSelectedItem();
        avaibdocs.removeAllItems();
        for (Doctor doc : DoctorManager.getInstance().getAllDoctors()) {
            avaibdocs.addItem(doc.toString());
        }
        if (previousSelection != null) {
            avaibdocs.setSelectedItem(previousSelection);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        else if (e.getSource() == btnRemove) {
            int selectedRow = tab.getSelectedRow();
            if (selectedRow != -1) {
                patres.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "No appointments to remove.");
            }
        }
        else if (e.getSource() == btnAdd) {
            String name = patnameF.getText().trim();
            String ageText = patageF.getText().trim();
            String gender = (String) patgender.getSelectedItem();
            String concern = patillnessF.getText().trim();
            String history = medhistF.getText().trim();
            String date = apptschedF.getText().trim();
            String doctor = (String) avaibdocs.getSelectedItem();

            if (name.isEmpty() || ageText.isEmpty() || concern.isEmpty() || date.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
                return;
            }

            try {
                int patientAge = Integer.parseInt(ageText);

                // AUTO-GENERATE APPOINTMENT & PATIENT ID
                int autoGeneratedId = AppointmentManager.getInstance().generateNextAppointmentId();
                String generatedIdStr = String.valueOf(autoGeneratedId);

                // 1. Save Patient Locally & to MySQL
                Patient newPatient = new Patient(autoGeneratedId, name, patientAge, gender);
                systemDataStructures.addPatient(newPatient);
                
                PatientDAO patientDAO = new PatientDAO();
                patientDAO.insert(newPatient);

                // 2. Get selected urgency string (e.g., "4 - Critical")
                String selectedUrgencyStr = (String) urgencyComboBox.getSelectedItem();

                // 3. Create Appointment object with String urgency
                Appointments newAppt = new Appointments(
                    name, 
                    patientAge, 
                    gender, 
                    generatedIdStr, 
                    (doctor == null) ? "Not selected" : doctor, 
                    "General", 
                    history, 
                    concern, 
                    date, 
                    selectedUrgencyStr 
                );
                newAppt.setAppointmentId(autoGeneratedId);
                
                // 4. Save to Database
                AppointmentDAO dao = new AppointmentDAO();
                boolean dbSuccess = dao.insert(newAppt);

                // 5. Add to Priority Queue
                AppointmentManager.getInstance().addAppointment(newAppt);

                if (!dbSuccess) {
                    JOptionPane.showMessageDialog(this, "Warning: Patient added locally, but failed to save appointment to MySQL database.");
                }

                // 6. Update table view
                patres.addRow(new Object[]{
                    name, 
                    ageText,
                    gender,
                    date, 
                    selectedUrgencyStr, 
                    (doctor == null || doctor.isEmpty()) ? "Not selected" : doctor
                });

                // Clear fields
                patnameF.setText("");
                patageF.setText("");
                patillnessF.setText("");
                medhistF.setText("");
                apptschedF.setText("");
                urgencyComboBox.setSelectedIndex(0);

                JOptionPane.showMessageDialog(this, "Appointment booked successfully! (Auto-Assigned ID: " + autoGeneratedId + ")");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Patient Age must be a valid number.", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}