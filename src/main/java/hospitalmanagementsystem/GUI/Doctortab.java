/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.util.LinkedList;
import javax.swing.*;
// Gives access to the registered doctors, used to check the login
import hospitalmanagementsystem.logic.DoctorManager;
// Doctor model, used for the doctor who logs in
import hospitalmanagementsystem.model.Doctor;

/**
 *
 * @author Mikeyks
 */
public class Doctortab extends JFrame implements ActionListener{
    private JLabel txtTop, logName, logPass;
    // Password box hides the typed characters
    private JTextField txtName;
    private JPasswordField txtPass;
    private JButton btnLogin, btnBack;
    private JPanel backgroundPanel;
    //comment
    
    public Doctortab(){
        
        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(900, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        this.setLocationRelativeTo(this);
        
        txtTop = new JLabel("Doctor Log-In");
        txtTop.setBounds(350, 100, 280, 50);
        txtTop.setFont(new Font("Western", Font.PLAIN, 36));
        add(txtTop);
        
        logName = new JLabel("Username:");
        logName.setBounds(200, 200, 100, 20);
        logName.setFont(new Font("Western", Font.PLAIN, 20));
        add(logName);
        
        txtName = new JTextField();
        txtName.setBounds(320, 195, 280, 30);
        txtName.setFont(new Font("Western", Font.PLAIN, 20));
        add(txtName);
        
        logPass = new JLabel("Password:");
        logPass.setBounds(200, 260, 100, 20);
        logPass.setFont(new Font("Western", Font.PLAIN, 20));
        add(logPass);
        
        txtPass = new JPasswordField();
        txtPass.setBounds(320, 255, 280, 30);
        txtPass.setFont(new Font("Western", Font.PLAIN, 20));
        add(txtPass);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(320, 350, 100, 40);
        add(btnBack);
        
        btnLogin = new JButton("Log-In");
        btnLogin.setBounds(500, 350, 100, 40);
        add(btnLogin);
        
        btnBack.addActionListener(this);
        btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack){
            hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        else if (e.getSource()== btnLogin) {
            
            // Checks the typed username and password against the doctors registered in DoctorManager
            String username = txtName.getText().trim();
            String password = new String(txtPass.getPassword());
            
            // Stops here and shows a message if either box is empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your username and password.");
                return;
            }
            
            // Asks DoctorManager for the doctor with this login; null means no doctor matches
            Doctor loggedIn = DoctorManager.getInstance().login(username, password);
            if (loggedIn == null) {
                JOptionPane.showMessageDialog(this, "Invalid username or password.",
                        "Log-In Failed", JOptionPane.ERROR_MESSAGE);
                txtPass.setText("");
                return;
            }
            
            //Caller part to DoctorHomepage
            // Passes the logged-in doctor so the home page can show their name, specialty and ID
            hospitalmanagementsystem.GUI.Doctorhomepage app =new hospitalmanagementsystem.GUI.Doctorhomepage(loggedIn);
            this.setVisible(false);
           app.setVisible(true);
        }
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // (the throw statement that was here ran after every click, so it was removed)
    }
    
}