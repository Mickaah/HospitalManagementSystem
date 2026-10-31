/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
// Doctor model, used to show the logged-in doctor's details
import hospitalmanagementsystem.model.Doctor;

/**
 *
 * @author Mikeyks
 */
public class Doctorhomepage extends JFrame implements ActionListener {
    private JLabel greeting, docname, docspec, patientlist, DocID, Src;
    private DefaultTableModel patientsched;
    private JButton btnSearch,  btnBack;
    private JTextField SrcField;
    private JPanel backgroundPanel;
  
    // Opens the home page without a doctor, so the placeholder texts stay on the labels
    Doctorhomepage() {
        this(null);
    }
    
    // Opens the home page for the doctor who logged in
    Doctorhomepage(Doctor doctor) {
        setSize(870, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        this.setLocationRelativeTo(this);
        
        greeting = new JLabel("Hello, Welcome,");
        greeting.setBounds(10, 5, 280, 50);
        greeting.setFont(new Font("Western", Font.PLAIN, 20));
        add(greeting);
        
        docname = new JLabel("Dr. [name]");
        docname.setBounds(157, 5, 280, 50);
        docname.setFont(new Font("Western", Font.PLAIN, 20));
        add(docname);
        
        docspec = new JLabel("[doc specialty]");
        docspec.setBounds(10, 30, 280, 50);
        docspec.setFont(new Font("Western", Font.PLAIN, 16));
        add(docspec);
        
        DocID = new JLabel("ID number: ");
        DocID.setBounds(670, 10, 280, 50);
        DocID.setFont(new Font("Western", Font.PLAIN, 16));
        add(DocID);
        
        // Replaces the placeholder texts with the logged-in doctor's name, specialty and ID
        if (doctor != null) {
            docname.setText("Dr. " + doctor.getName());
            docspec.setText(doctor.getSpecialty());
            DocID.setText("ID number: " + doctor.getId());
        }
        
        Src = new JLabel("Search Appointments");
        Src.setBounds(10, 57, 200, 50);
        Src.setFont(new Font("Western", Font.PLAIN, 14));
        add(Src);
        
        SrcField = new JTextField();
        SrcField.setBounds(150, 72, 200, 20);
        SrcField.setFont(new Font("Western", Font.PLAIN, 14));
        add(SrcField);
        
        patientlist = new JLabel("current appointments: ");
        patientlist.setBounds(10, 90, 280, 50);
        patientlist.setFont(new Font("Western", Font.PLAIN, 14));
        add(patientlist);
        
        patientsched = new DefaultTableModel();
        patientsched.addColumn("patient name:");
        patientsched.addColumn("patient age:");
        patientsched.addColumn("Appointment Date");
        patientsched.addColumn("Patient ID:");
        JTable tab = new JTable(patientsched);
        JScrollPane scroll = new JScrollPane(tab);
        scroll.setBounds(10, 130, 720, 305);
        scroll.setFont(new Font("Western", Font.PLAIN, 14));
        add(scroll);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(750, 340, 90, 50);
        add(btnSearch); 
        
        btnBack = new JButton("Back");
        btnBack.setBounds(760, 400, 70, 50);
        add(btnBack); 
        
        btnBack.addActionListener(this);
        btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
             hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        
        if (e.getSource() == btnSearch){
            //logic here!!
        }
    }
}