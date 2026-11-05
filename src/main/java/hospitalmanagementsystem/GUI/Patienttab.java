/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.dsa.SystemDataStructures;
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
    private JLabel txtTitle, patname, patage, patgenlab, patillness, medhist, apptsched, docappt, patID;
    private JComboBox<String> patgender, avaibdocs;
    private JTextField patnameF, patageF, apptschedF, patIDF;
    private JTextArea patillnessF, medhistF; 
    private DefaultTableModel patres;
    private JTable tab; 
    private JButton btnBack, btnAdd, btnRemove;
    private JPanel backgroundPanel;
    private ImageIcon image;
    private String[] gend = {"male", "female", "trans(male/female)"}; 
    private String[] avaib = {};
    
    private SystemDataStructures systemDataStructures;

    public Patienttab() {
        this(new SystemDataStructures());
    }

    public Patienttab(SystemDataStructures sharedDataStructures) {
        this.systemDataStructures = (sharedDataStructures != null) ? sharedDataStructures : new SystemDataStructures();

        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(870, 500);
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
        txtTitle.setBounds(10, 5, 280, 50);
        txtTitle.setFont(new Font("Western", Font.PLAIN, 20));
        add(txtTitle);
        
        patname = new JLabel("Patient's name: ");
        patname.setBounds(10, 40, 190, 50);
        patname.setFont(new Font("Western", Font.BOLD, 14));
        add(patname);
        
        patID = new JLabel("Patient's ID: ");
        patID.setBounds(400, 77, 190, 50);
        patID.setFont(new Font("Western", Font.BOLD, 14));
        add(patID);
        
        patIDF = new JTextField();
        patIDF.setBounds(488, 93, 250, 20);
        patIDF.setFont(new Font("Western", Font.BOLD, 14));
        add(patIDF);
        
        patnameF = new JTextField();
        patnameF.setBounds(120, 55, 250, 20);
        patnameF.setFont(new Font("Western", Font.BOLD, 14));
        add(patnameF);
        
        patage = new JLabel("patient's age: ");
        patage.setBounds(10, 60, 200, 50);
        patage.setFont(new Font("Western", Font.BOLD, 14));
        add(patage);
        
        patageF = new JTextField();
        patageF.setBounds(110, 75, 250, 20);
        patageF.setFont(new Font("Western", Font.BOLD, 14));
        add(patageF);
        
        patgenlab = new JLabel("patient's gender: ");
        patgenlab.setBounds(10, 80, 200, 50);
        patgenlab.setFont(new Font("Western", Font.BOLD, 14));
        add(patgenlab);
        
        patgender = new JComboBox<>(gend);
        patgender.setBounds(133, 97, 200, 20);
        patgender.setFont(new Font("Western", Font.BOLD, 14));
        add(patgender);
        
        patillness = new JLabel("concern: ");
        patillness.setBounds(482, 120, 200, 50);
        patillness.setFont(new Font("Western", Font.BOLD, 14));
        add(patillness);
        
        patillnessF = new JTextArea();
        patillnessF.setBounds(545, 141, 270, 60);
        patillnessF.setFont(new Font("Western", Font.BOLD, 14));
        add(patillnessF);
        
        medhist = new JLabel("Previous Medical History: ");
        medhist.setBounds(10, 120, 200, 50);
        medhist.setFont(new Font("Western", Font.BOLD, 14));
        add(medhist);
        
        medhistF = new JTextArea();
        medhistF.setBounds(191, 141, 270, 60);
        medhistF.setFont(new Font("Western", Font.BOLD, 14));
        add(medhistF);
        
        apptsched = new JLabel("Set Appointment Date (MM/DD/YY): ");
        apptsched.setBounds(10, 207, 270, 50);
        apptsched.setFont(new Font("Western", Font.BOLD, 15));
        add(apptsched);
        
        apptschedF = new JTextField();
        apptschedF.setBounds(260, 220, 350, 30);
        apptschedF.setFont(new Font("Western", Font.BOLD, 14));
        add(apptschedF);
        
        docappt = new JLabel("See Doctor: ");
        docappt.setBounds(400, 40, 190, 50);
        docappt.setFont(new Font("Western", Font.BOLD, 14));
        add(docappt);
        
        avaibdocs = new JComboBox<>(avaib);
        avaibdocs.setBounds(488, 55, 250, 20);
        avaibdocs.setFont(new Font("Western", Font.BOLD, 14));
        add(avaibdocs);
        
        loadDoctorsIntoComboBox();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                loadDoctorsIntoComboBox();
            }
        });
        
        patres = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patres.addColumn("patient name:");
        patres.addColumn("patient age:");
        patres.addColumn("patient gender:");
        patres.addColumn("Appointment Date");
        patres.addColumn("See Doctor:");
        
        tab = new JTable(patres);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.getTableHeader().setResizingAllowed(false);
        tab.getTableHeader().setBackground(Color.decode("#bdfdc5"));
        tab.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane scroll = new JScrollPane(tab);
        scroll.setBounds(10, 275, 720, 160);
        scroll.setFont(new Font("Western", Font.PLAIN, 14));
        add(scroll);
                
        btnBack = new JButton("Back");
        btnBack.setBounds(770, 400, 70, 50);
        add(btnBack); 
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(770, 280, 70, 50);
        add(btnAdd); 
        
        btnRemove = new JButton("Remove");
        btnRemove.setBounds(750, 340, 90, 50);
        add(btnRemove); 
        
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
            String idText = patIDF.getText().trim();
            String name = patnameF.getText().trim();
            String ageText = patageF.getText().trim();
            String gender = (String) patgender.getSelectedItem();
            String concern = patillnessF.getText().trim();
            String history = medhistF.getText().trim();
            String date = apptschedF.getText().trim();
            String doctor = (String) avaibdocs.getSelectedItem();

            if (idText.isEmpty() || name.isEmpty() || ageText.isEmpty() || concern.isEmpty() || date.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields, including Patient ID.");
                return;
            }

            try {
                int patientId = Integer.parseInt(idText);
                int patientAge = Integer.parseInt(ageText);

                if (systemDataStructures.verifyPatientExists(patientId)) {
                    JOptionPane.showMessageDialog(this, 
                        "A patient with ID " + patientId + " already exists!", 
                        "Duplicate Record Error", 
                        JOptionPane.WARNING_MESSAGE);
                    return; 
                }

                // 1. Save Patient to DSA/Memory structure
                Patient newPatient = new Patient(patientId, name, patientAge, gender);
                systemDataStructures.addPatient(newPatient);

                // 2. CRITICAL: Save Appointment to MySQL Database via AppointmentDAO
                Appointments newAppt = new Appointments(
                    name, 
                    patientAge, 
                    gender, 
                    idText, 
                    (doctor == null) ? "Not selected" : doctor, 
                    "General", // Default specialty
                    history, 
                    concern, 
                    date, 
                    1 // Default urgency level
                );
                
                AppointmentDAO dao = new AppointmentDAO();
                boolean dbSuccess = dao.insert(newAppt);

                if (!dbSuccess) {
                    JOptionPane.showMessageDialog(this, "Warning: Patient added locally, but failed to save to MySQL database.");
                }

                // 3. Update visual table model
                patres.addRow(new Object[]{
                    name, 
                    ageText,
                    gender,
                    date, 
                    (doctor == null || doctor.isEmpty()) ? "Not selected" : doctor
                });

                // Clear input fields after successful addition
                patIDF.setText("");
                patnameF.setText("");
                patageF.setText("");
                patillnessF.setText("");
                medhistF.setText("");
                apptschedF.setText("");

                JOptionPane.showMessageDialog(this, "Appointment added and saved to database successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Patient ID and Age must be valid numbers.", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}