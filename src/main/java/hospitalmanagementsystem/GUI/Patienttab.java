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
    private JLabel txtTitle, patname, patage, patgenlab, patillness, medhist, apptsched, docappt;
    private JComboBox<String> patgender, avaibdocs;
    private JTextField patnameF, patageF, apptschedF;
    private JTextArea patillnessF, medhistF, patres;
    private JButton btnBack, btnAdd, btnRemove;
    private String[] gend = {"male", "female", "trans(male/female)"}; 
    private String[] avaib = {};
    
 public Patienttab() {
    setTitle("HOSPITAL MANAGEMENT APP");
        setSize(870, 500);
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
        patillness.setBounds(450, 120, 200, 50);
        patillness.setFont(new Font("Western", Font.PLAIN, 14));
        add(patillness);
        
        patillnessF= new JTextArea();
        patillnessF.setBounds(510, 141, 270, 60);
        patillnessF.setFont(new Font("Western", Font.PLAIN, 14));
        add(patillnessF);
        
        medhist= new JLabel("Previous Medical History: ");
        medhist.setBounds(10, 120, 200, 50);
        medhist.setFont(new Font("Western", Font.PLAIN, 14));
        add(medhist);
        
        medhistF= new JTextArea();
        medhistF.setBounds(170, 141, 270, 60);
        medhistF.setFont(new Font("Western", Font.PLAIN, 14));
        add(medhistF);
        
        apptsched= new JLabel("Set Appointment Date (MM/DD/YY): ");
        apptsched.setBounds(10, 207, 250, 50);
        apptsched.setFont(new Font("Western", Font.PLAIN, 15));
        add(apptsched);
        
        apptschedF= new JTextField();
        apptschedF.setBounds(240, 220, 350, 30);
        apptschedF.setFont(new Font("Western", Font.PLAIN, 14));
        add(apptschedF);
        
        docappt = new JLabel("See Doctor: ");
        docappt.setBounds(400, 40, 190, 50);
        docappt.setFont(new Font("Western", Font.PLAIN, 14));
        add(docappt);
        
        avaibdocs = new JComboBox<>(avaib);
        avaibdocs.setBounds(480, 55, 250, 20);
        avaibdocs.setFont(new Font("Western", Font.PLAIN, 14));
        add(avaibdocs);
        
        patres = new JTextArea();
        patres.setEditable(false);
        JScrollPane scroll = new JScrollPane(patres);
        scroll.setBounds(10, 275, 720, 170);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack){
            hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        else if(e.getSource() == btnAdd){
            
        }
        
        else if(e.getSource() == btnRemove){
            
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
