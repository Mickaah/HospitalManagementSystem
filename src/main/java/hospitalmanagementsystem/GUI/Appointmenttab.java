package hospitalmanagementsystem.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mikeyks
 */
public class Appointmenttab extends JFrame implements ActionListener {
    private JLabel title, seeappt, search;
    private JTextField searchF;
    private DefaultTableModel sched;
    private JButton btnsrch, btnback;
    private DefaultListModel<String> src;
    private LinkedList<String> srchlst;
    private JTable tab;
    private JPanel backgroundPanel;
    
    Appointmenttab() {
        setTitle("APPOINTMENT MANAGER");
        setSize(870, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        this.setLocationRelativeTo(this);
        
        title = new JLabel("Welcome!");
        title.setBounds(10, 5, 280, 50);
        title.setFont(new Font("Western", Font.PLAIN, 20));
        add(title);
        
        seeappt = new JLabel("see appointments here: ");
        seeappt.setBounds(10, 30, 280, 50);
        seeappt.setFont(new Font("Western", Font.PLAIN, 16));
        add(seeappt);
        
        search = new JLabel("Search Appointments");
        search.setBounds(10, 57, 200, 50);
        search.setFont(new Font("Western", Font.PLAIN, 14));
        add(search);
        
        searchF = new JTextField();
        searchF.setBounds(150, 74, 200, 20);
        searchF.setFont(new Font("Western", Font.PLAIN, 14));
        add(searchF);
        
        sched = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        sched.addColumn("patient name:");
        sched.addColumn("doctor name:");
        sched.addColumn("specialty:");
        sched.addColumn("Appointment Date");
        sched.addColumn("ID:"); 
        
        tab = new JTable(sched);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.getTableHeader().setResizingAllowed(false);
        tab.getTableHeader().setBackground(Color.decode("#bdfdc5"));
        tab.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane scroll = new JScrollPane(tab);
        scroll.setBounds(10, 100, 720, 335);
        scroll.setFont(new Font("Western", Font.PLAIN, 14));
        add(scroll);
        
        btnsrch = new JButton("Search");
        btnsrch.setBounds(750, 340, 90, 50);
        add(btnsrch); 
        
        btnback = new JButton("Back");
        btnback.setBounds(760, 400, 70, 50);
        add(btnback); 
        
        btnsrch.addActionListener(this);
        btnback.addActionListener(this);
        
       
        loadAppointmentsFromDatabase();
    }
   
    private void loadAppointmentsFromDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_accs?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM appointments");
            
            
            sched.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("patient_name"),
                    rs.getString("doctor_name"),
                    rs.getString("specialty"),
                    rs.getString("appointment_date"),
                    rs.getInt("id")
                };
                sched.addRow(row);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointments from database.");
        }
    }
   
   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnsrch) {
            // Pwede lagyan ng search logic dito gamit ang searchF.getText()
        }    
         if (e.getSource() == btnback) {
           hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
       }
    }
}