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

import javax.swing.*;

/**
 *
 * @author Mikeyks
 */
public class HospitalSysApp extends JFrame {
    private JLabel Title, patpage, docpage, aptpage,
            patname, patage, patgender, patillness, medhist, apptsched,
            docname, docspec, patientlist,
            aptID, patID, DocID, DoA;
    private JTextField TitleF, patpageF, docpageF, aptpageF,
            patnameF, patageF, patgenderF, patillnessF, medhistF, apptschedF,
            docnameF, docspecF, patientlistF,
            aptIDF, patIDF, DocIDF, DoAF; 
    
    HospitalSysApp () {
        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(600, 700);
        setLayout(null);
        setVisible(true);
    }
}
