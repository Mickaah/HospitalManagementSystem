/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import hospitalmanagementsystem.dsa.SystemDataStructures;
// Gives access to the shared list of registered doctors
import hospitalmanagementsystem.logic.DoctorManager;
// Appointment model, used to save each booking in the system's data structures
import hospitalmanagementsystem.model.Appointments;
// Doctor model, used when looping through the registered doctors
import hospitalmanagementsystem.model.Doctor;
import hospitalmanagementsystem.model.Patient;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Window listener classes, used to refresh the doctor list when the screen is shown again
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// Lists used to keep the appointments that match the rows of the table
import java.util.ArrayList;
import java.util.List;
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
    private JTable tab; // Class-level variable so actionPerformed can access it
    private JButton btnBack, btnAdd, btnRemove;
    private JPanel backgroundPanel;
    private String[] gend = {"male", "female", "trans(male/female)"}; 
    private String[] avaib = {};
    
    // ADDED: Shared backend instance for BST and HashMap management
    private SystemDataStructures systemDataStructures;
    
    // Appointments in the same order as the table rows, so Remove deletes the right record
    private List<Appointments> shownAppointments = new ArrayList<>();

    // Default constructor
    public Patienttab() {
        // Uses the shared instance so patients and appointments are kept between screens
        this(SystemDataStructures.getInstance());
    }

    // Constructor accepting a shared SystemDataStructures instance
    public Patienttab(SystemDataStructures sharedDataStructures) {
        this.systemDataStructures = (sharedDataStructures != null) ? sharedDataStructures : SystemDataStructures.getInstance();

        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(870, 500);
        setLayout(null);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        
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
        
        // Fills the See Doctor drop-down with the doctors registered in DoctorManager
        loadDoctorsIntoComboBox();
        
        // Reloads the doctor list each time this window becomes active, so doctors registered
        // elsewhere show up without reopening the app
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
        
        // Shows the appointments already saved in the system, so the table is not empty
        // when this screen is opened again after going Back
        for (Appointments a : systemDataStructures.getAppointmentHistory()) {
            shownAppointments.add(a);
            patres.addRow(new Object[]{
                a.getPatientName(),
                a.getPatientAge(),
                a.getPatientGender(),
                a.getAppointmentDate(),
                a.getDoctorName()
            });
        }
        
        tab = new JTable(patres);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.getTableHeader().setResizingAllowed(false);
        
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
    
    // Rebuilds the See Doctor drop-down from DoctorManager's list of doctors,
    // keeping the current selection if that doctor is still in the list
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
                // Deletes the appointment from the system's data structures, not just from the table
                systemDataStructures.removeAppointment(shownAppointments.get(selectedRow));
                shownAppointments.remove(selectedRow);
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

            // 1. Check for missing required input fields
            if (idText.isEmpty() || name.isEmpty() || ageText.isEmpty() || concern.isEmpty() || date.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields, including Patient ID.");
                return;
            }

            try {
                int patientId = Integer.parseInt(idText);

                // 2. CHECK FOR DUPLICATE PATIENTS via SystemDataStructures
                if (systemDataStructures.verifyPatientExists(patientId)) {
                    JOptionPane.showMessageDialog(this, 
                        "A patient with ID " + patientId + " already exists!", 
                        "Duplicate Record Error", 
                        JOptionPane.WARNING_MESSAGE);
                    return; // Prevent duplicate row insertion
                }

                // 3. Create Patient object and insert into system DSA
                // Assuming the constructor order is: (int id, String name, int age, String gender) 
// Adjust the parameters to match your Patient.java class constructor order if needed:
int patientAge = Integer.parseInt(ageText);
// Uses the constructor that keeps gender and medical history in separate fields
Patient newPatient = new Patient(patientId, name, patientAge, gender, history);
                
                systemDataStructures.addPatient(newPatient); // Updates BST & HashMap

                // Finds the Doctor object that matches the selected drop-down item (same order as DoctorManager's list)
                Doctor selectedDoctor = null;
                List<Doctor> allDoctors = DoctorManager.getInstance().getAllDoctors();
                int doctorIndex = avaibdocs.getSelectedIndex();
                if (doctorIndex >= 0 && doctorIndex < allDoctors.size()) {
                    selectedDoctor = allDoctors.get(doctorIndex);
                }
                String doctorName = (selectedDoctor != null) ? selectedDoctor.getName() : "Not selected";

                // Creates the appointment (urgency level 1 by default, since the form has no urgency field)
                // and saves it in the priority queue and history, the patient's record and the doctor's patient list
                Appointments newAppointment = new Appointments(name, patientAge, gender,
                        String.valueOf(patientId), doctorName, history, concern, date, 1);
                systemDataStructures.addAppointment(newAppointment, newPatient, selectedDoctor);
                shownAppointments.add(newAppointment);

                // 4. Update visual table model
                patres.addRow(new Object[]{
                    name, 
                    ageText,
                    gender,
                    date, 
                    doctorName
                });

                // Clear input fields after successful addition
                patIDF.setText("");
                patnameF.setText("");
                patageF.setText("");
                patillnessF.setText("");
                medhistF.setText("");
                apptschedF.setText("");

                JOptionPane.showMessageDialog(this, "Appointment added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Patient ID and Age must be valid numbers.", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}