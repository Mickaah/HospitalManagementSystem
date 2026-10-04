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
package com.mycompany.hospitalsys;

import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Mikeyks
 */
public class HospitalSysApp extends JFrame {
    private JLabel Title, landing, patpage, docpage, aptpage,
            patname, patage, patgender, patillness, medhist, apptsched,
            docname, docspec, patientlist,
            aptID, patID, DocID, DoA;
    private JTextField TitleF, patpageF, docpageF, aptpageF,
            patnameF, patageF, patgenderF, patillnessF, medhistF, apptschedF,
            docnameF, docspecF, patientlistF,
            aptIDF, patIDF, DocIDF, DoAF; 
   private JButton gtpatient, gtdoc, gtappt;
    
    HospitalSysApp () {
        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(600, 600);
        setLayout(null);
        setVisible(true);
        
        Title = new JLabel("Welcome to [name] Hospital!");
        Title.setBounds(160, 5, 280, 80);
        Title.setFont(new Font("Western", Font.PLAIN, 20));
        add(Title);
        
        landing = new JLabel("How can we help you?");
        landing.setBounds(185, 80, 280, 80);
        landing.setFont(new Font("Western", Font.PLAIN, 20));
        add(landing);
        
        gtpatient = new JButton("Book Appointment (Patient)");
        gtpatient.setBounds(130, 200, 320, 70);
        gtpatient.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtpatient);
        
        gtdoc= new JButton("Staff Log in (Doctor)");
        gtdoc.setBounds(130, 300, 320, 70);
        gtdoc.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtdoc);
        
        gtappt = new JButton("See Appointment Dates");
        gtappt.setBounds(130, 400, 320, 70);
        gtappt.setFont(new Font("Western", Font.PLAIN, 20));
        add(gtappt);
    }
}
