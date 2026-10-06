/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.util.LinkedList;
import javax.swing.*;
/**
 *
 * @author Mikeyks
 */
public class Doctortab extends JFrame implements ActionListener{
    private JLabel txtTop, logName, logPass;
    private JTextField txtName, txtPass;
    private JButton btnLogin, btnBack;
    
    public Doctortab(){
        setTitle("HOSPITAL MANAGEMENT APP");
        setSize(900, 500);
        setLayout(null);
        setVisible(true);
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
        
        txtPass = new JTextField();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}