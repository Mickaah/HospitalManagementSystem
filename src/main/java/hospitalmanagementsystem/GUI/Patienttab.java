/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Mikeyks
 */
public class Patienttab extends JFrame implements ActionListener {
    private JLabel txtTitle, patname, patage, patgenlab, patillness, medhist, apptsched;
    private JComboBox<String> patgender;
    private JTextField patnameF, patageF, patgenderF, patillnessF, medhistF, apptschedF;
    private JButton btnBack;
    private String[] gend = {"male", "female", "trans(male/female)"}; 
    
 public Patienttab() {
    setTitle("HOSPITAL MANAGEMENT APP");
        setSize(900, 700);
        setLayout(null);
        setVisible(true);
        this.setLocationRelativeTo(this);
        
        txtTitle = new JLabel("Book an Appointment");
        txtTitle.setBounds(10, 5, 280, 50);
        txtTitle.setFont(new Font("Western", Font.PLAIN, 20));
        add(txtTitle);
        
        patname = new JLabel("Patient's name: ");
        patname.setBounds(10, 40, 190, 50);
        patname.setFont(new Font("Western", Font.PLAIN, 14));
        add(patname);
        
        patnameF = new JTextField();
        patnameF.setBounds(120, 55, 250, 20);
        patnameF.setFont(new Font("Western", Font.PLAIN, 14));
        add(patnameF);
        
        patage= new JLabel("patient's age: ");
        patage.setBounds(10, 60, 200, 50);
        patage.setFont(new Font("Western", Font.PLAIN, 14));
        add(patage);
        
        patageF= new JTextField();
        patageF.setBounds(110, 75, 250, 20);
        patageF.setFont(new Font("Western", Font.PLAIN, 14));
        add(patageF);
        
        patgenlab= new JLabel("patient's gender: ");
        patgenlab.setBounds(10, 80, 200, 50);
        patgenlab.setFont(new Font("Western", Font.PLAIN, 14));
        add(patgenlab);
        
        patgender= new JComboBox<>(gend);
        patgender.setBounds(120, 97, 200, 20);
        patgender.setFont(new Font("Western", Font.PLAIN, 14));
        add(patgender);
        
        patillness= new JLabel("concern: ");
        patillness.setBounds(10, 100, 200, 50);
        patillness.setFont(new Font("Western", Font.PLAIN, 14));
        add(patillness);
        
        patillnessF= new JTextField();
        patillnessF.setBounds(80, 117, 250, 20);
        patillnessF.setFont(new Font("Western", Font.PLAIN, 14));
        add(patillnessF);
        
        medhist= new JLabel("Previous Medical History: ");
        medhist.setBounds(10, 120, 200, 50);
        medhist.setFont(new Font("Western", Font.PLAIN, 14));
        add(medhist);
        
        medhistF= new JTextField();
        medhistF.setBounds(170, 143, 200, 50);
        medhistF.setFont(new Font("Western", Font.PLAIN, 14));
        add(medhistF);
        
        apptsched= new JLabel("Appointment Date: ");
        apptsched.setBounds(10, 200, 200, 50);
        apptsched.setFont(new Font("Western", Font.PLAIN, 14));
        add(apptsched);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(800, 600, 70, 50);
        add(btnBack);
        
        btnBack.addActionListener(this);
        
 }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack){
            hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
